<template>
<div>
	<!-- SERVER ERROR -->
	<h3 class="text-danger" v-if="serverError">SERVER ERROR</h3>

	<!-- SEARCHBAR -->
	<button v-if="!searching" @click="searching = true" class="btn btn-primary">SEARCH</button>
	<form v-else>
		<input type="text" v-model="searching_text">
		<input @click="search" class="btn btn-primary" type="submit" value="SEARCH">
		<input @click="searchCancel" class="btn btn-danger" type="submit" value="CANCEL">
	</form>

	<!-- PHOTO LIST -->
	<ul>
		<hr>
		<li v-for="photo in photos" :key="photo.id">
			<!-- PHOTO TITLE -->
			<h3>{{ photo.title }}</h3>

			<!-- PHOTO IMG -->
			<div>
				<img :src="photo.url">
			</div>

			<!-- PHOTO TAGS (if present) -->
			<div v-if="photo.tags.length > 0">
				<h4>Tag: {{ photo.tags.length }}</h4>
				<ul>
					<li v-for="tag in photo.tags" :key="tag.id">
						{{ tag.name }}
					</li>
				</ul>
			</div>

			<!-- PHOTO CATEGORIES (if present) -->
			<div v-if="photo.categories.length > 0">
				<h4>Category: {{ photo.categories.length }}</h4>
				<ul>
					<li v-for="cat in photo.categories" :key="cat.id">
						{{ cat.name }}
					</li>
				</ul>
			</div>

			<!-- PHOTO COMMENTS (if present) -->
			<div v-if="photo.comments.length > 0">
				<h4>Comments: {{ photo.comments.length }}</h4>
				<ul>
					<li v-for="comment in photo.comments" :key="comment.id">
						<h4>
							{{ comment.username }}
						</h4>
						<span>
							{{ comment.text }}
						</span>
					</li>
				</ul>
			</div>
 
			<!-- POST NEW COMMENT FORM -->
			<div v-if="photo_comment_id == photo.id" class="commentForm">
				<h1>Post a comment</h1>
				<div v-if="comment_form_errors.length > 0" class="text-danger">
					<h3>Errors:</h3>
					<ul>
						<li 
							v-for="(error, index) in comment_form_errors" 
							:key="index"
						>
							{{ error }}
						</li>
					</ul>
				</div>
				<form>
					<label for="username">Username</label>
					<br>
					<input type="text" name="username" v-model="comment_username">
					<br>
					<label for="text">text</label>
					<br>
					<textarea type="text" name="text" v-model="comment_text" />
					<br>
					<input 
						@click="saveComment" 
						type="submit" 
						value="SAVE" 
						class="btn btn-primary"
					>
					<input 
						@click="cancelComment" 
						type="submit" 
						value="CANCEL" 
						class="btn btn-danger"
					>
				</form>
			</div>        

			<!-- OPEN COMMENT FORM BUTTON -->
			<div v-else class="commentOpenButton">
				<button 
				@click="photo_comment_id = photo.id" 
				class="btn btn-primary"
			>COMMENT</button>
			</div>
			<hr>
		</li>
	</ul>
</div>
</template>

<script>

import axios from 'axios';

const API_URL = 'http://localhost:8080/api/1';
const PHOTO_COMMENT_ID_DEF = -1;

export default {
	name: 'PhotosComp',
	data() {

		return {
			serverError: false, // got a server error

			photos: [], // list of printing photo
			photos_backup: [], // list of all downloaded photos as backup

			photo_comment_id: PHOTO_COMMENT_ID_DEF, // comment form photo id

			comment_username: "", // comment user's username
			comment_text: "", // comment user's text

			searching: false, // search form visibility
			searching_text: "", // search text

			comment_form_errors: [], // comment form validation errors
		};
	},
	methods: {

		// get all photos from API
		getAllPhotos() {

			axios.get(API_URL + '/photo/all')
				.then(res => {

					const photos = res.data;
					this.photos = this.photos_backup = photos;
				})
				.catch(() => this.serverError = true)
		},

		// search for photo index by id
		getPhotoIndexById(id) {

			console.log("id: " + id);
			const photos = this.photos_backup;
			for (let x = 0; x < photos.length; x++) {

				const photo = photos[x];

				if (photo.id == id)
					return x;
			}

			return -1;
		},

		// search for photo by id
		getPhotoById(id) {

			return this.photos_backup[this.getPhotoIndexById(id)];
		},

		// close & clear comment form
		clearCommentForm() {

			this.comment_username = this.comment_text = "";
			this.photo_comment_id = PHOTO_COMMENT_ID_DEF;
		},
		cancelComment(e) {

			e.preventDefault();
			this.clearCommentForm();
		},

		// save new comment on db
		saveComment(e) {

			e.preventDefault();

			// collect info
			const id = this.photo_comment_id;
			const username = this.comment_username.trim();
			const text = this.comment_text.trim();

			// input validation
			let errors = this.comment_form_errors = [];
			if (username.length < 3) {

				errors.push("Username must be between 3 and 64 charters");
			}
			if (text.length < 3) {

				errors.push("Text must be at least 3 charters");
			}
			// if there are some errors, block request and print errors
			if (errors.length) {

				this.comment_form_errors = errors;
				return;
			}

			// build a db compatible message object
			const msg = {
				'username': username,
				'text': text
			};

			// send object to API
			axios.post(API_URL + "/photo/" + id + "/comment/post", msg)
				.then(res => {

					// res.data contain new saved comment
					const comment = res.data;
					const photo = this.getPhotoById(id);

					// push comment in printing photo list
					photo.comments.push(comment)
					this.clearCommentForm();
				})
				.catch(() => {

					// server error, print error in different ways
					this.comment_form_errors = [ "Error during comment post" ];
					this.serverError = true;
				});
		},

		// to get a real vue-style search avoid 
		// API call in order to perform a research, 
		// because all information needed is already
		// downloaded
		// don't waste bandwidth and server,
		// use client-pc power instead
		// (only for small amount of data and whenever client
		// has already downloaded ALL data in which research 
		// will be performed)
		search(e) {

			e.preventDefault();

			// make a result array
			const searchPhoto = [];
			// clean search text
			const searchText = this.searching_text.trim();
			// get all photos
			const photos = this.photos_backup;

			// if search text is empty get all photos
			if (searchText.length < 1) {

				this.photos = photos;
				return;
			}

			// check all photos
			for (let x = 0; x < photos.length; x++) {

				const photo = photos[x];
				const photoTitle = photo.title;

				// if title include search text push photo and
				// go on with next one
				if (photoTitle.includes(searchText)) {

					searchPhoto.push(photo);
					continue;
				}

				// check for tags
				const tags = photo.tags;
				for (let y = 0; y < tags.length; y++) {

					const tag = tags[y];
					console.log("TAG: " + JSON.stringify(tag));

					// if tag name include search text push photo
					// and sto nested for to avoid photo duplication
					if (tag.name.includes(searchText)) {

						searchPhoto.push(photo);
						break;
					}
				}
			}

			// set resulting photos in photos printing array
			this.photos = searchPhoto;
		},
		// clear & close search form
		searchCancel(e) {

			e.preventDefault();

			this.searching = false;
			this.searching_text = "";
			this.photos = this.photos_backup;
		},
	},
	mounted() {

		this.getAllPhotos();
	}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

img {

	max-width: 300px;
	max-height: 300px;
}

.inline {

	display: inline;
}

</style>