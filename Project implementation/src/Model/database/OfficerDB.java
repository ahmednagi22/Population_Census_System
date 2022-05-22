package Model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Entities.CorrectionRequest;
import Model.Entities.FamilyMember;
import Model.Entities.Member;
import Model.Entities.Officer;
import Model.Entities.System_user;
import Model.Entities.User;
import Model.Entities.UserRequest;

public class OfficerDB {

    public static Connection connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OfficerDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection("jdbc:sqlite:Population Census System DB.db");
    }

    public static void addCorrectionRequest(CorrectionRequest correctionRequest) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("insert into CorrectionRequest(UserRequestID,RequestTitle,RequestContent,userID,officerID) values(?,?,?,?,?)"); PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setInt(1, correctionRequest.getUserRequestID());
            p.setString(2, correctionRequest.getRequestTitle());
            p.setString(3, correctionRequest.getRequestContent());
            p.setInt(4, correctionRequest.getUserID());
            p.setInt(5, correctionRequest.getOfficerID());

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

    }

    public static void deleteCorrectionRequest(int requestID) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("delete from CorrectionRequest where requestID = ? "); PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setInt(1, requestID);

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

    }

    public static void updateCorrectionRequest(CorrectionRequest correctionRequest) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("UPDATE CorrectionRequest SET RequestTitle = ?,RequestContent = ? WHERE RequestID = ?"); PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setString(1, correctionRequest.getRequestTitle());
            p.setString(2, correctionRequest.getRequestContent());
            p.setInt(3, correctionRequest.getRequestID());
            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

    }

    public static ArrayList<User> getUsersByParameters(boolean hasdescendantsOrHasUsers, String SortByOption, boolean ascendingOrder, int adminID) {
        ArrayList<User> Users = new ArrayList<>();
        String inOrNotIn = hasdescendantsOrHasUsers ? "IN" : "NOT IN";
        String ASCOrDESC = ascendingOrder ? "ASC" : "DESC";
        String query = "SELECT * FROM User WHERE userID " + inOrNotIn + "(SELECT userID FROM Member) " + " AND officerID in (select officerID from Officer where adminID = " + adminID + " ) " + " ORDER BY " + SortByOption + "  " + ASCOrDESC;
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement(query);) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {      //return  one row of officer table 

                    Users.add(new User(r.getInt("officerID"), r.getInt("userID"), r.getString("Address"), r.getString("Education"), r.getString("Email"), r.getString("Sex"), r.getString("Occupation"), r.getString("DOB"), r.getInt("AreaID"), r.getString("Name"), r.getString("Phone"), r.getString("Username"), r.getString("Password")));

                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return Users;
    }

    public static int getAreaIDFromOfficerID(int officerID) {
        int areaID = 0;
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("select AreaID from Officer where officerID = ?");) {
            p.setInt(1, officerID);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                areaID = r.getInt("AreaID");
            }

        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return areaID;
    }

    public static void addUser(User user) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("insert into User(Address,Education,Phone,Email,DOB,Name,AreaID,Sex,Occupation,Username,Password,officerID) values(?,?,?,?,?,?,?,?,?,?,?,?)"); PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setString(1, user.getAddress());
            p.setString(2, user.getEducation());
            p.setString(3, user.getPhone());
            p.setString(4, user.getEmail());
            p.setString(5, user.getDOB());
            p.setString(6, user.getName());
            p.setInt(7, user.getAreaID());
            p.setString(8, user.getSex());
            p.setString(9, user.getOccupation());
            p.setString(10, user.getUsername());
            p.setString(11, user.getPassword());
            p.setInt(12, user.getOfficerID());
            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }
    }

    public static boolean hasCorrectionRequest(int requestID) {
        CorrectionRequest correctionRequest = new CorrectionRequest();
        correctionRequest.setRequestID(-1);
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("select RequestID from CorrectionRequest where UserRequestID = " + requestID);) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {      //return  one row of Area table 
                    correctionRequest.setRequestID(r.getInt("RequestID"));
                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return correctionRequest.getRequestID() != -1;
    }

    public static void updateStateOfUserRequestToRejected(int requestID) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("UPDATE UserRequest SET RequestState = \"Rejected\" WHERE RequestID = ?"); PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setInt(1, requestID);

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }
    }

    public static void updateStateOfUserRequestToAccepted(int requestID) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("UPDATE UserRequest SET RequestState = \"Accepted\" WHERE RequestID = ?"); PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setInt(1, requestID);

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }
    }

    public static void deleteCorrectionRequestByUserRequestID(int userRequestID) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("delete from CorrectionRequest where UserRequestID = ? "); PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setInt(1, userRequestID);

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }
    }

    public ArrayList<CorrectionRequest> getCorrectionRequests() {
        ArrayList<CorrectionRequest> CorrectionRequests = new ArrayList<>();
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("select * from CorrectionRequest");) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {      //return  one row of Area table 

                    CorrectionRequests.add(new CorrectionRequest(r.getInt("RequestID"), r.getInt("UserRequestID"), r.getString("RequestTitle"), r.getString("RequestContent"), r.getInt("userID"), r.getInt("officerID")));

                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return CorrectionRequests;
    }

    public static ArrayList<System_user> getSystemUsers() {
        ArrayList<System_user> systemUsers = new ArrayList<>();
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("SELECT Username,Password,adminID\n"
                        + "FROM Admin\n");
                PreparedStatement p1 = con.prepareStatement("SELECT Username,Password,userID\n"
                        + "FROM User\n");
                PreparedStatement p2 = con.prepareStatement("SELECT Username,Password,officerID\n"
                        + "FROM Officer\n");) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {

                    systemUsers.add(new System_user(r.getString("Username"), r.getString("Password"), "Admin", r.getInt("adminID")));

                }
                r.close();
                ResultSet r1 = p1.executeQuery();
                while (r1.next()) {

                    systemUsers.add(new System_user(r1.getString("Username"), r1.getString("Password"), "User", r1.getInt("userID")));

                }
                r1.close();
                ResultSet r2 = p2.executeQuery();
                while (r2.next()) {

                    systemUsers.add(new System_user(r2.getString("Username"), r2.getString("Password"), "Officer", r2.getInt("officerID")));

                }
                r2.close();
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return systemUsers;

    }

    public static ArrayList<FamilyMember> getFamilyMembers(int officerID) {
        ArrayList<FamilyMember> FamilyMembers = new ArrayList<>();
        try ( //(String city, String address, String education, String email, String sex, String occupation, String DocName, Date DOB, int areaID, String name, int phone, String imageName, String Email
                Connection con = connect(); PreparedStatement p = con.prepareStatement("SELECT *\n"
                        + "FROM User\n"
                        + "WHERE User.officerID=" + officerID);
                PreparedStatement p1 = con.prepareStatement("SELECT *\n"
                        + "FROM Member,User\n"
                        + "WHERE Member.userID=User.userID AND User.officerID=" + officerID);) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {
                    FamilyMembers.add(new User(r.getInt("officerID"), r.getInt("userID"), r.getString("Address"), r.getString("Education"), r.getString("Email"), r.getString("Sex"), r.getString("Occupation"), r.getString("DOB"), r.getInt("AreaID"), r.getString("Name"), r.getString("Phone"), r.getString("Username"), r.getString("Password")));

                }
                ResultSet r2 = p1.executeQuery();
                while (r2.next()) {

                    FamilyMembers.add(new Member(r.getInt("memberID"), r.getInt("userID"), r.getString("Address"), r.getString("Education"), r.getString("Email"), r.getString("Sex"), r.getString("Occupation"), r.getString("DOB"), r.getInt("AreaID"), r.getString("Name"), r.getString("Phone")));

                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return FamilyMembers;

    }

    public static boolean check(String username, String password) {
        ArrayList<System_user> users = OfficerDB.getSystemUsers();
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername()) && password.equals(users.get(i).getPassword())) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<UserRequest> getPendingUserRequests(int officerID) {
        ArrayList<UserRequest> userRequests = new ArrayList<>();
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("select * from UserRequest where userID in (select userID from User where officerID = " + officerID + " And RequestState = \"Pending\")");) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {
                    userRequests.add(new UserRequest(r.getInt("RequestID"), r.getString("RequestState"), r.getString("Name"), r.getString("Address"), r.getString("Education"), r.getString("Phone"), r.getString("Email"), r.getInt("memberID"), r.getString("DOB"), r.getInt("AreaID"), r.getString("Sex"), r.getString("Occupation"), r.getInt("userID")));

                }
                r.close();
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());
        }

        return userRequests;

    }

    public static CorrectionRequest getCorrectionRequest(int userRequestID) {
        CorrectionRequest correctionRequest = new CorrectionRequest();
        correctionRequest.setOfficerID(-1);
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("SELECT * FROM CorrectionRequest where UserRequestID = " + userRequestID);) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {
                    correctionRequest = new CorrectionRequest(r.getInt("RequestID"), r.getInt("UserRequestID"), r.getString("RequestTitle"), r.getString("RequestContent"), r.getInt("userID"), r.getInt("officerID"));
                }
                r.close();
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());
        }
        return correctionRequest;
    }

}
