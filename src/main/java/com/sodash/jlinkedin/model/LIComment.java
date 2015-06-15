/**
 * 
 */
package com.sodash.jlinkedin.model;

import java.util.List;

import winterwell.json.JSONObject;
import winterwell.utils.TodoException;
import winterwell.utils.time.Time;

import com.sodash.jlinkedin.fields.MessageType;


/**
 * Unify all the many types of message LI offers
 * @author daniel
 *
 */
public class LIComment extends LIPostBase {

	public LIComment(JSONObject base) {
		super(base);		
	}
	
}
