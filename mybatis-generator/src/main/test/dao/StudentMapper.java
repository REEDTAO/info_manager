package test.dao;

import test.model.Student;

public interface StudentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbg.generated Tue Apr 09 16:48:59 CST 2019
     */
    int deleteByPrimaryKey(String studentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbg.generated Tue Apr 09 16:48:59 CST 2019
     */
    int insert(Student record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbg.generated Tue Apr 09 16:48:59 CST 2019
     */
    int insertSelective(Student record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbg.generated Tue Apr 09 16:48:59 CST 2019
     */
    Student selectByPrimaryKey(String studentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbg.generated Tue Apr 09 16:48:59 CST 2019
     */
    int updateByPrimaryKeySelective(Student record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbg.generated Tue Apr 09 16:48:59 CST 2019
     */
    int updateByPrimaryKey(Student record);
}