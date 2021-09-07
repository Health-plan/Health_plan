package com.health.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Account implements UserDetails{
	private String mbrId;
	private String mbrPw;
	private int failCnt;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnabled;
	
	private Collection <? extends GrantedAuthority> authorities;

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return this.authorities;
		}
		
		public String getMbrId() {
			return mbrId;
		}

		public void setMbrId(String mbr_id) {
			this.mbrId = mbr_id;
		}

		public void setMbrPw(String mbr_pw) {
			this.mbrPw = mbr_pw;
		}

		public void setAccountNonExpired(boolean isAccountNonExpired) {
			this.isAccountNonExpired = true;
		}

		public void setAccountNonLocked(boolean isAccountNonLocked) {
			this.isAccountNonLocked = true;
		}

		public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
			this.isCredentialsNonExpired = true;
		}

		public void setEnabled(boolean isEnabled) {
			this.isEnabled = true;
		}

		public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
			this.authorities = authorities;
		}

		public int getFailCnt() {
			return failCnt;
		}

		public void setFailCnt(int failCnt) {
			this.failCnt = failCnt;
		}

		// UserDetails의 필수 메서드들
		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return this.mbrPw;
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return this.mbrId;
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return this.isAccountNonExpired;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return this.isAccountNonLocked;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return this.isCredentialsNonExpired;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return this.isEnabled;
		}

		@Override
		public String toString() {
			return "Account [mbrId=" + mbrId + ", mbrPw=" + mbrPw + ", failCnt=" + failCnt + ", isAccountNonExpired="
					+ isAccountNonExpired + ", isAccountNonLocked=" + isAccountNonLocked + ", isCredentialsNonExpired="
					+ isCredentialsNonExpired + ", isEnabled=" + isEnabled + ", authorities=" + authorities + "]";
		}
		
		
}
