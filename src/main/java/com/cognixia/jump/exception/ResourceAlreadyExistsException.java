package com.cognixia.jump.exception;

/**
 * The custom exception for a resource that already exists.
 * @author Lori White
 * @version v1 (08/25/2020)
 */
public class ResourceAlreadyExistsException extends Exception{
	private static final long serialVersionUID = 5777514691224236672L;
	/**
	 * The overloaded constructor.
	 * @author Lori White
	 * @param message the custom error message
	 */
	public ResourceAlreadyExistsException(String message) {
		super(message);
	}
}
