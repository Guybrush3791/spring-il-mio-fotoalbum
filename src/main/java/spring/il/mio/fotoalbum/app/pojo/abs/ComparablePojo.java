package spring.il.mio.fotoalbum.app.pojo.abs;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import spring.il.mio.fotoalbum.app.pojo.security.Role;

// this class make abstraction only at java-level
// is now possible to describe some logic and
// share it to every pojo, to avoid code redundancy

// there is many outcame possible for class inheritance and
// abstraction in DB, so make sure to use appropriate annotation
// to describe tables structure and relations

@MappedSuperclass
public abstract class ComparablePojo {

	// -- SHARED ID LOGIC -- \\
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	// --------------------- \\
	
	// -- SHARED LOGIC TO GET STRING FROM RELATION -- \\
	// to use a generic collection as parameter, is necessary
	// to define generic type: <E extends ComparablePojo>
	// E is now a type of data, any extension of ComparablePojo
	// is valid as E
	protected <E extends ComparablePojo> String getStrFromRelation(
		Collection<E> objs, // this rappresent a generic collection of ComparablePojo
		Function<E, String> mapper, // this function rappresent a way to 
									// map a single pojo in a String;
									// note this function has to use
									// the same type of Set as incoming
									// parameter
		String emptyStr	// predefined empty string
	) {
		
		return (objs == null || objs.isEmpty()) // if set of relation is null or empty
				? emptyStr // return predefined empty string
				: objs.stream() // open a stream on set 
						.map(mapper) // map each element of set with mapper to get
									 // a string for each element
					.collect(Collectors.joining(" - ")) // join all resulting string
														// with " - " as separator
				;
	}
	// ---------------------------------------------- \\
	
	// -- SHARED COMPARABLE LOGIC ID BASED -- \\
	@Override
	public int hashCode() {
		
		return getId();
	}
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Role)) return false;
		
		return hashCode() == obj.hashCode();
	}
	// -------------------------------------- \\
	
	// -- SHARED toString LOGIC -- \\
	@Override
	public String toString() {
		
		return "(" + getId() + ") ";
	}
	// --------------------------- \\
}
