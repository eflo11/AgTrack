package com.agtrack.service;

import com.agtrack.model.Grower;
import com.agtrack.utilities.ConnectionManager;
import org.apache.commons.dbutils.GenerousBeanProcessor;

import java.sql.*;

public class ServiceGrower {

    public static Grower getGrower(String email) throws SQLException {
        Grower grower = null;

        String sql = "SELECT * FROM GROWER \r" +
                "WHERE email = ?";
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                grower = new GenerousBeanProcessor().toBean(rs, Grower.class);
            }else{
                grower = new Grower();
            }

            grower.setCrops(ServiceCrop.getCrops(grower.getGrowerId()));


        }catch (SQLException e){
            System.out.println("There was an issue getting your account information.");
        }

        return grower;
    }

    public static Grower insertGrower(Grower grower) throws SQLException {

        String sql = "INSERT INTO GROWER (user, name, password, address, address2, city, state, zip) \n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            pstmt.setString(1, grower.getUser());
            pstmt.setString(2, grower.getName());
            pstmt.setString(3, grower.getPassword());
            pstmt.setString(4, grower.getAddress());
            pstmt.setString(5, grower.getAddress2());
            pstmt.setString(6, grower.getCity());
            pstmt.setString(7, grower.getState());
            pstmt.setString(8, grower.getZip());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                grower.setGrowerId(rs.getInt(1));
            }


        }catch (SQLException e){
            System.out.println("There was an issue getting your account information.");
        }

        return grower;
    }

    public static Grower updateGrower(Grower grower) throws SQLException {

        String sql = "UPDATE GROWER SET \r" +
                "user = ?, \r" +
                "name = ?, \r" +
                "password = ?, \r" +
                "address = ?, \r" +
                "address2 = ?, \r" +
                "city = ?, \r" +
                "state = ?, \r" +
                "zip = ? \r";
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, grower.getUser());

            pstmt.executeUpdate();


        }catch (SQLException e){
            System.out.println("There was an issue getting your account information.");
        }

        return grower;
    }

    public static void deleteGrower(int growerNumber) throws SQLException {
        Grower grower = null;

        String sql = "DELETE FROM GROWER \r" +
                "WHERE growerId = ?";
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, growerNumber);

            pstmt.executeUpdate();


        }catch (SQLException e){
            System.out.println("There was an issue deleting your account.");
        }
    }
}
