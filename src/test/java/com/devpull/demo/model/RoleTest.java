package com.devpull.demo.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoleTest {

	@Test
	public void test() {
	final String roleName = "ADMIN";
	final Role role = new Role();
	role.setId(1);
	role.setRoleName(roleName);

	}

}
