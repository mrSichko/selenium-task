import entity.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    private static final String SQL__FIND_COMMENT_BY_NAME =
            "SELECT * FROM comment WHERE login=?";

    private static final String SQL__FIND_COMMENT_BY_ID =
            "SELECT * FROM comment WHERE id=?";

    private static final String SQL__FIND_ALL_COMMENTS =
            "SELECT * FROM comment";

    public Comment getCommentById(int id){
        Connection connection = Repository.getConnection();
        Comment comment = new Comment();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL__FIND_COMMENT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet =  statement.executeQuery();
            resultSet.next();
            comment.setId(resultSet.getInt("id"));
            comment.setName(resultSet.getString("name"));
            comment.setContent(resultSet.getString("content"));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Repository.closeConnection();
        }
        return comment;
    }

    public Comment getCommentByName(String name){
        Connection connection = Repository.getConnection();
        Comment comment = new Comment();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL__FIND_COMMENT_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet =  statement.executeQuery();
            resultSet.next();
            comment.setId(resultSet.getInt("id"));
            comment.setName(resultSet.getString("name"));
            comment.setContent(resultSet.getString("content"));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Repository.closeConnection();
        }
        return comment;
    }

    public List<Comment> getAllComments(){
        List<Comment> list = new ArrayList<>();
        Connection connection = Repository.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL__FIND_ALL_COMMENTS);
            ResultSet resultSet =  statement.executeQuery();
            Comment comment;
            while (resultSet.next()) {
                comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setName(resultSet.getString("name"));
                comment.setContent(resultSet.getString("content"));
                list.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Repository.closeConnection();
        }
        return list;
    }
}
