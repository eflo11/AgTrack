package com.agtrack.service;


import com.agtrack.model.Crop;
import com.agtrack.utilities.ConnectionManager;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.wrappers.StringTrimmedResultSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCrop {


    public static List<Crop> getCrops(int growerNumber){
        List<Crop> crops = new ArrayList<>();

        String sql = "SELECT * FROM CROP " +
                "WHERE growerId = ?";

        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, growerNumber);

            ResultSet rs = StringTrimmedResultSet.wrap(pstmt.executeQuery());
            crops = new GenerousBeanProcessor().toBeanList(rs, Crop.class);


        }catch (SQLException e){
            System.out.print("Error in generating sql for grabbing all the crops.");
        }


        return crops;
    }

    public static Crop getCrop(int cropId){
        Crop crop = null;

        String sql = "SELECT * FROM CROP " +
                "WHERE id = ?";

        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, cropId);

            ResultSet rs = StringTrimmedResultSet.wrap(pstmt.executeQuery());
            if(rs.next()) {
                crop = new GenerousBeanProcessor().toBean(rs, Crop.class);
            }else{
                crop = new Crop();
            }


        }catch (SQLException e){
            System.out.print("Error in generating sql for grabbing all the crops.");
        }

        return crop;
    }

    public static Crop insertCrop(int growerId, Crop crop){

        String sql = "INSERT INTO CROP " +
                "(growthTime, latitude, longitude, name, type, variety, growerId, plantDate) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            pstmt.setInt(1, crop.getGrowthTime());
            pstmt.setString(2, crop.getLatitude());
            pstmt.setString(3, crop.getLongitude());
            pstmt.setString(4, crop.getName());
            pstmt.setString(5, crop.getType());
            pstmt.setString(6, crop.getVariety());
            pstmt.setInt(7, growerId);
            pstmt.setDate(8, crop.getPlantDate());


            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                crop.setId(rs.getInt(1));
            }

        }catch (SQLException e){
            System.out.print("Error in generating sql for grabbing all the crops.");
        }

        return crop;
    }


    public static void deleteCrop(int cropId) throws Exception {

        String sql = "DELETE FROM CROP " +
                "WHERE cropId = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cropId);

            pstmt.executeUpdate();

        }catch (Exception e){
            throw new Exception("Unable to delete crop.");
        }
    }
}
