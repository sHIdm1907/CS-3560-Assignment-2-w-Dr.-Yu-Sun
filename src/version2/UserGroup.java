package version2;

import java.util.ArrayList;
import java.util.List;

public class UserGroup implements SysEntry {
	
	private List<SysEntry> list = new ArrayList<SysEntry>();
	private List<User> groupUsers = new ArrayList<User>();
	private List<UserGroup> subGroups = new ArrayList<UserGroup>();
	private String id;
	
	//constructor to take in a string whenever a new
	//UserGroup object is created
	public UserGroup(String name) {
		id = name;
	}//end UserGroup
	
	
	//adds a user into the group
	//if the user name is already in the group, a message is printed instead
	public void addUser(User name) {
		if(groupUsers.contains(name)) {
			System.out.println("This user is already in this group");
		}
		else {
			groupUsers.add(name);
		}
		
	}//end addUser
	
	
	//adds a subgroup into the group
	public void addGroup(UserGroup name) {
		subGroups.add(name);
	}//end addGroup
	
	
	//gets the displayName of a group
	public String getID() {
		return id;
	}//end getID

	
	//sets the display name for a group
	public void setID(String id) {
		this.id = id;
	}//end setID



	//getter for subGroups
	public List<UserGroup> getGroup(){
		return subGroups;
	}//end getGroup
	
	//getter for groupUsers
	public List<User> getUserList(){
		return groupUsers;
	}//end getUserList


	@Override
	public int accept(ShowGroupTotalVisitor visitor) {
		int total = visitor.visit(this.getGroup());
		return total;
	}//end accept


	@Override
	public int accept(ShowMessageTotalVisitor visitor) {
		int total = 0;
		for(User user: this.getUserList()) {
			total += visitor.visit(user.getMessageCounter());
		}
		return total;
		
	}


	@Override
	public float accept(ShowPositivePercentVisitor visitor, List<String> list) {
		float total = 0;
		for(User user: this.getUserList()) {
			total += visitor.visit(user.getFeed(), list);
		}
		return  total;
		
	}


	@Override
	public int accept(ShowUserTotalVisitor visitor) {
		int total = visitor.visit(this.getUserList());
		return total;
	}

	
	
	
}
