package com.AuthRole.Auth.model.MapStruct;

import com.AuthRole.Auth.model.Comment;
import com.AuthRole.Auth.model.Response.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);


    CommentResponse toResponse(Comment comment);
}
