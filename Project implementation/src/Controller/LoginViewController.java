package Controller;

import Model.database.AdminDB;
import Model.database.OfficerDB;
import Model.Entities.System_user;
import View.Admin.AddOfficerView;
import View.Officer.AddUserView;
import View.User.FamilyMembersView;
import java.util.ArrayList;
import javafx.stage.Stage;

/**
 *
 * @author ahmad
 */
public class LoginViewController {

    public boolean checkIfUserExsist(String userName, String password) {
        return OfficerDB.check(userName, password);
    }

    public void openSystemUserAccount(String username, String password) {
        String accountType = getAccountType(username, password);
        if (accountType.equals("Admin")) {
            int adminID = AdminDB.getAdminIDfromUsername(username);
            AddOfficerView c1 = new AddOfficerView(adminID);
            c1.start(new Stage());
        } else if (accountType.equals("Officer")) {
            int officerID = AdminDB.getOfficerIDfromUsername(username);
            AddUserView c1 = new AddUserView(officerID);
            c1.start(new Stage());
        } else if (accountType.equals("User")) {
            int userID = AdminDB.getUserIDfromUsername(username);
            FamilyMembersView c1 = new FamilyMembersView(userID);
            c1.start(new Stage());
        }
    }

    private String getAccountType(String userName, String password) {
        String Accounttype = "";
        ArrayList<System_user> allAccounts = OfficerDB.getSystemUsers();
        for (int i = 0; i < allAccounts.size(); i++) {
            if (allAccounts.get(i).getUsername().equals(userName) && allAccounts.get(i).getPassword().equals(password)) {
                Accounttype = allAccounts.get(i).getType();
                break;
            }
        }
        return Accounttype;
    }
}
