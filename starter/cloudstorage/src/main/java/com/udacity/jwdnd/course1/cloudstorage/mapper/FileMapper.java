package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.UploadedFile;
import org.apache.ibatis.annotations.*;


@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    UploadedFile getFile(String fileName);

    @Select("SELECT filename FROM FILES WHERE userid = #{userId}")
    String[] getFileListings(Integer userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(UploadedFile file);

    @Delete("DELETE FROM FILES WHERE filename = #{fileName}")
    void deleteFile(String fileName);
}
