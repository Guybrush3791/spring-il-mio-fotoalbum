<template>
	<h1>PHOTO ALBUM</h1>
	<PhotosComp />

</template>

<script>

import "bootstrap/dist/css/bootstrap.min.css";
import PhotosComp from './components/PhotosComp.vue'

export default {
	name: 'App',
	components: {
		PhotosComp
	}
}

</script> 

<style>

#app {
	font-family: Avenir, Helvetica, Arial, sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	text-align: center;
	color: #2c3e50;
	margin-top: 60px;
}

</style>
