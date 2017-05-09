package model;

public class Login {

	private String name ;
	
	public Login(String name){
		this.name = name ;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name ;
	}
	
	public boolean equals(Login user){
		return this.getName().equals(user.getName());
	}

}
