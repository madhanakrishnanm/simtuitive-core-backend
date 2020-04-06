package com.simtuitive.core.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RedisHash("PasswordResetToken")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordResetToken implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String email;
	    private String token;
	    private String salt;
	    private Date expirationTime;

	    public PasswordResetToken(String email, String token,String salt, Date expirationTime) {
	        this.email = email;
	        this.token = token;
	        this.salt = salt;
	        this.expirationTime = expirationTime;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }

	    public Date getExpirationTime() {
	        return expirationTime;
	    }

	    public void setExpirationTime(Date expirationTime) {
	        this.expirationTime = expirationTime;
	    }

		/**
		 * @return the salt
		 */
		public String getSalt() {
			return salt;
		}

		/**
		 * @param salt the salt to set
		 */
		public void setSalt(String salt) {
			this.salt = salt;
		}

		public PasswordResetToken() {
			
			// TODO Auto-generated constructor stub
		}

}
