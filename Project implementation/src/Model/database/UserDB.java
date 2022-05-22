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
import Model.Entities.Member;
import Model.Entities.UserRequest;

/**
 *
 * @author PC
 */
public class UserDB {

    public static Connection connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection("jdbc:sqlite:Population Census System DB.db");
    }

    public static void addMember(Member member) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("insert into Member(Address,Education,Phone,Email,memberID,DOB,Name,AreaID,Sex,Occupation,userID) values(?,?,?,?,?,?,?,?,?,?,?)");
                PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setString(1, member.getAddress());
            p.setString(2, member.getEducation());
            p.setString(3, member.getPhone());
            p.setString(4, member.getEmail());
            p.setInt(5, getNewIDForMember());
            p.setString(6, member.getDOB());
            p.setString(7, member.getName());
            p.setInt(8, member.getAreaID());
            p.setString(9, member.getSex());
            p.setString(10, member.getOccupation());
            p.setInt(11, member.getUserID());
            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

    }

    public static void deleteMember(int memberID) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("delete from Member where memberID = ? ");
                PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setInt(1, memberID);

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

    }
//(Address,City,Education,Phone,Email,DOB,Name,AreaID,Sex,Occupation,userID,DocName,ImageName)

    public static void updateMember(Member member) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("UPDATE Member SET Address = ?,Education = ?,Phone = ? ,Email = ?,DOB = ?,Name = ?,AreaID = ?,Sex = ?,Occupation = ?,userID = ? WHERE memberID= ?");
                PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setString(1, member.getAddress());
            p.setString(2, member.getEducation());
            p.setString(3, member.getPhone());
            p.setString(4, member.getEmail());
            p.setString(5, member.getDOB());
            p.setString(6, member.getName());
            p.setInt(7, member.getAreaID());
            p.setString(8, member.getSex());
            p.setString(9, member.getOccupation());
            p.setInt(10, member.getUserID());
            p.setInt(11, member.getMemberID());
            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

    }

    public static ArrayList<Member> getMembers(int userID) {
        ArrayList<Member> members = new ArrayList<>();
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("select * from Member where userID = " + userID);) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {      //return  one row of Area table 
                    members.add(new Member(r.getInt("memberID"), r.getInt("userID"), r.getString("Address"), r.getString("Education"), r.getString("Email"), r.getString("Sex"), r.getString("Occupation"), r.getString("DOB"), r.getInt("AreaID"), r.getString("Name"), r.getString("Phone")));
                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return members;
    }

    public static int getNewIDForMember() {
        int memberID = 0;
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("select seq from sqlite_sequence where name = \"Member\"");) {
            ResultSet r = p.executeQuery();
            while (r.next()) {
                memberID = r.getInt("seq");
            }

        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return memberID + 1;
    }

    // where is delete and update UserRequest ???????
    public static void addUserRequest(UserRequest userRequest) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("insert into UserRequest(RequestState,Name,Address,Education,Phone,Email,memberID,DOB,AreaID,Sex,Occupation,userID) values(?,?,?,?,?,?,?,?,?,?,?,?)");
                PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setString(1, userRequest.getRequestState());
            p.setString(2, userRequest.getName());
            p.setString(3, userRequest.getAddress());
            p.setString(4, userRequest.getEducation());
            p.setString(5, userRequest.getPhone());
            p.setString(6, userRequest.getEmail());
            p.setInt(7, getNewIDForMember());
            p.setString(8, userRequest.getDOB());
            p.setInt(9, userRequest.getAreaID());
            p.setString(10, userRequest.getSex());
            p.setString(11, userRequest.getOccupation());
            p.setInt(12, userRequest.getUserID());
            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

    }

    public static void updateUserRequest(UserRequest userRequest) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("UPDATE UserRequest SET Name = ?,RequestState = ?,Address = ?, Education = ?,Phone = ?,Email=?,DOB=?,Sex=?,Occupation = ? where RequestID = ?");
                PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setString(1, userRequest.getName());
            p.setString(2, userRequest.getRequestState());
            p.setString(3, userRequest.getAddress());
            p.setString(4, userRequest.getEducation());
            p.setString(5, userRequest.getPhone());
            p.setString(6, userRequest.getEmail());
            p.setString(7, userRequest.getDOB());
            p.setString(8, userRequest.getSex());
            p.setString(9, userRequest.getOccupation());
            p.setInt(10, userRequest.getRequestID());

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }
    }

    public static void deleteUserRequest(int requestID) {
        try (
                Connection con = connect();
                PreparedStatement p1 = con.prepareStatement("delete from UserRequest where requestID = ? ");) {
            p1.setInt(1, requestID);
            p1.execute();

        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

    }

    public static ArrayList<UserRequest> getUserRequests() {
        ArrayList<UserRequest> userRequests = new ArrayList<>();
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("select * from UserRequest");) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {      //return  one row of Area table 
                    userRequests.add(new UserRequest(r.getInt("RequestID"), r.getString("RequestState"), r.getString("Name"), r.getString("Address"), r.getString("Education"), r.getString("Phone"), r.getString("Email"), r.getInt("memberID"), r.getString("DOB"), r.getInt("AreaID"), r.getString("Sex"), r.getString("Occupation"), r.getInt("userID")));

                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return userRequests;

    }

    public static ArrayList<CorrectionRequest> getCorrectionRequests(int userID) {
        ArrayList<CorrectionRequest> correctionRequests = new ArrayList<>();
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("select * from CorrectionRequest where userID = " + userID);) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {
                    correctionRequests.add(new CorrectionRequest(r.getInt("RequestID"), r.getInt("UserRequestID"), r.getString("RequestTitle"), r.getString("RequestContent"), r.getInt("userID"), r.getInt("officerID")));

                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return correctionRequests;

    }

    public static int getAreaIDFromUserID(int userID) {
        int areaID = 0;
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("select AreaID from User where userID = ?");) {
            p.setInt(1, userID);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                areaID = r.getInt("AreaID");
            }

        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return areaID;
    }

    public static boolean check(String username, String password) {

        return OfficerDB.check(username, password);
    }

    public static UserRequest getUserRequestForUser(int userRequestID) {
        UserRequest userRequest = new UserRequest();
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("select * from UserRequest where RequestID = " + userRequestID);) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {      //return  one row of Area table 
                    userRequest = new UserRequest(r.getInt("RequestID"), r.getString("RequestState"), r.getString("Name"), r.getString("Address"), r.getString("Education"), r.getString("Phone"), r.getString("Email"), r.getInt("memberID"), r.getString("DOB"), r.getInt("AreaID"), r.getString("Sex"), r.getString("Occupation"), r.getInt("userID"));
                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return userRequest;
    }

    public static ArrayList<UserRequest> getUserRequestsForUser(int userID) {
        ArrayList<UserRequest> userRequests = new ArrayList<>();
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("select * from UserRequest where userID = " + userID);) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {      //return  one row of Area table 
                    userRequests.add(new UserRequest(r.getInt("RequestID"), r.getString("RequestState"), r.getString("Name"), r.getString("Address"), r.getString("Education"), r.getString("Phone"), r.getString("Email"), r.getInt("memberID"), r.getString("DOB"), r.getInt("AreaID"), r.getString("Sex"), r.getString("Occupation"), r.getInt("userID")));

                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return userRequests;
    }

}
