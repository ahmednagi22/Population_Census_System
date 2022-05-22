package Model.database;

import Model.Entities.Admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Entities.Area;
import Model.Entities.FamilyMember;
import Model.Entities.Member;
import Model.Entities.Officer;
import Model.Entities.State;
import Model.Entities.User;

public class AdminDB {

    public static Connection connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection("jdbc:sqlite:Population Census System DB.db");
    }

    public static void addOfficer(Officer officer) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("insert into Officer(Phone,Email,Name,AreaID,Sex,Username,Password,adminID) values(?,?,?,?,?,?,?,?)"); PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setString(1, officer.getPhone());
            p.setString(2, officer.getEmail());
            p.setString(3, officer.getName());
            p.setInt(4, officer.getAreaID());
            p.setString(5, officer.getSex());
            p.setString(6, officer.getUsername());
            p.setString(7, officer.getPassword());
            p.setInt(8, officer.getAdminID());
            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }
    }

    public static void deleteOfficer(int officerID) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("delete from Officer where officerID = ? "); PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setInt(1, officerID);

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }
    }

    public static void updateOfficer(Officer officer) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("UPDATE Officer SET Phone = ?,Email = ?,Name = ?,AreaID = ?,Sex = ?,Username = ?,Password = ?,adminID = ? WHERE officerID = ?"); PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setString(1, officer.getPhone());
            p.setString(2, officer.getEmail());
            p.setString(3, officer.getName());
            p.setInt(4, officer.getAreaID());
            p.setString(5, officer.getSex());
            p.setString(6, officer.getUsername());
            p.setString(7, officer.getPassword());
            p.setInt(8, officer.getAdminID());
            p.setInt(9, officer.getOfficerID());
            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

    }

    public static ArrayList<Officer> getOfficers(int adminID) {
        ArrayList<Officer> officers = new ArrayList<>();
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("select * from Officer where adminID = " + adminID);) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {      //return  one row of officer table 

                    officers.add(new Officer(r.getString("Phone"), r.getString("Email"), r.getInt("officerID"), r.getString("Name"), r.getInt("AreaID"), r.getString("Sex"), r.getString("Username"), r.getString("Password"), r.getInt("adminID")));

                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return officers;
    }

    public static void addArea(Area area) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("insert into Area(AreaName,AreaID,StateID) values(?,?,?)"); PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setString(1, area.getAreaName());
            p.setInt(2, area.getAreaID());
            p.setInt(3, area.getStateID());

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

    }

    public static void deleteArea(int areaID) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("delete from Area where AreaID = ? "); PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setInt(1, areaID);

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

    }

    public static void updateArea(Area area) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("UPDATE Area SET AreaName = ?,StateID = ? WHERE AreaID= ?"); PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setString(1, area.getAreaName());
            p.setInt(2, area.getStateID());
            p.setInt(3, area.getAreaID());
            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

    }

    public static ArrayList<Area> getAreasFromStateID(int stateID) {
        ArrayList<Area> areas = new ArrayList<>();
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("select * from Area where StateID = " + stateID);) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {      //return  one row of Area table 

                    areas.add(new Area(r.getString("AreaName"), r.getInt("AreaID"), r.getInt("StateID")));

                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return areas;
    }

    public static int getAreaID(String areaName) {
        int areaID = 0;
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("select AreaID from Area where AreaName = ?");) {
            p.setString(1, areaName);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                areaID = r.getInt("AreaID");
            }

        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return areaID;
    }

    public static String getAreaName(int areaID) {
        String areaName = "";
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("select AreaName from Area where AreaID = ?");) {
            p.setInt(1, areaID);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                areaName = r.getString("AreaName");
            }

        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return areaName;
    }

    public static void addState(State state) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("insert into State(StateName) values(?)"); PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setString(1, state.getStateName());

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

    }

    public static void deleteState(int StateID) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("delete from State where StateID = ? "); PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setInt(1, StateID);

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

    }

    public static void updateState(State state) {
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("UPDATE State SET StateName = ? WHERE StateID = ?"); PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setString(1, state.getStateName());
            p.setInt(2, state.getStateID());

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

    }

    public static ArrayList<State> getStates() {
        ArrayList<State> states = new ArrayList<>();
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement("select * from State");) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {      //return  one row of State table 

                    states.add(new State(r.getInt("StateID"), r.getString("StateName")));

                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return states;

    }

    public static ArrayList<FamilyMember> getFamilyMembers() {

        ArrayList<FamilyMember> FamilyMembers = new ArrayList<>();
        try ( //(String city, String address, String education, String email, String sex, String occupation, String DocName, Date DOB, int areaID, String name, int phone, String imageName, String Email
                Connection con = connect(); PreparedStatement p = con.prepareStatement("SELECT User.Address,User.Education,User.Email,User.Sex,User.Occupation,User.DOB,User.AreaID,User.Name,User.Phone\n"
                        + "FROM User,Area,Admin\n"
                        + "WHERE User.AreaID=Area.AreaID AND Area.StateID=Admin.StateID"); PreparedStatement p1 = con.prepareStatement("SELECT Member.Address,Member.Education,Member.Email,Member.Sex,Member.Occupation,Member.DOB,Member.AreaID,Member.Name,Member.Phone\n"
                        + "FROM Member,Area,Admin\n"
                        + "WHERE Member.AreaID=Area.AreaID AND Area.StateID=Admin.StateID");) {
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

    public static Admin getAdminFromAdminID(int adminID) {

        Admin admin = new Admin("", "", "", "");
        try ( //(String city, String address, String education, String email, String sex, String occupation, String DocName, Date DOB, int areaID, String name, int phone, String imageName, String Email
                Connection con = connect(); PreparedStatement p = con.prepareStatement("Select * from Admin where adminID = " + adminID);) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {

                    admin = new Admin(r.getInt("adminID"), r.getInt("StateID"), r.getString("Username"), r.getString("Password"), r.getString("Name"), r.getString("Phone"), r.getString("Email"), r.getString("Sex"));
                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return admin;

    }

    public static boolean check(String username, String password) {
        return OfficerDB.check(username, password);
    }

    public static ArrayList<Officer> getOfficersByParameters(boolean hasdescendantsOrHasUsers, String SortByOption, boolean ascendingOrder, int adminID) {
        ArrayList<Officer> officers = new ArrayList<>();
        String inOrNotIn = hasdescendantsOrHasUsers ? "IN" : "NOT IN";
        String ASCOrDESC = ascendingOrder ? "ASC" : "DESC";
        String query = "SELECT * FROM Officer WHERE officerID " + inOrNotIn + " (SELECT officerID FROM User) " + "And adminID = " + adminID + " ORDER BY " + SortByOption + " " + ASCOrDESC;
        try (
                Connection con = connect(); PreparedStatement p = con.prepareStatement(query);) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {      //return  one row of officer table 

                    officers.add(new Officer(r.getString("Phone"), r.getString("Email"), r.getInt("officerID"), r.getString("Name"), r.getInt("AreaID"), r.getString("Sex"), r.getString("Username"), r.getString("Password"), r.getInt("adminID")));

                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return officers;
    }

    public static int getAdminIDfromUsername(String username) {
        int adminID = 0;
        try ( //(String city, String address, String education, String email, String sex, String occupation, String DocName, Date DOB, int areaID, String name, int phone, String imageName, String Email
                Connection con = connect(); PreparedStatement p = con.prepareStatement("Select adminID from Admin where Username = ?");) {
            {
                p.setString(1, username);
                ResultSet r = p.executeQuery();
                while (r.next()) {

                    adminID = r.getInt("adminID");
                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return adminID;
    }

    public static int getOfficerIDfromUsername(String username) {
        int officerID = 0;
        try ( //(String city, String address, String education, String email, String sex, String occupation, String DocName, Date DOB, int areaID, String name, int phone, String imageName, String Email
                Connection con = connect(); PreparedStatement p = con.prepareStatement("Select officerID from Officer where Username = ?");) {
            {
                p.setString(1, username);
                ResultSet r = p.executeQuery();
                while (r.next()) {

                    officerID = r.getInt("officerID");
                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return officerID;
    }

    public static int getUserIDfromUsername(String username) {
        int userID = 0;
        try ( //(String city, String address, String education, String email, String sex, String occupation, String DocName, Date DOB, int areaID, String name, int phone, String imageName, String Email
                Connection con = connect(); PreparedStatement p = con.prepareStatement("Select userID from User where Username = ?");) {
            {
                p.setString(1, username);
                ResultSet r = p.executeQuery();
                while (r.next()) {

                    userID = r.getInt("userID");
                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }

        return userID;
    }

}
