//package com.vti.templaterestfulapi.database;
//
//import com.btect.fpt.cmsbackend.models.*;
//import com.btect.fpt.cmsbackend.repositories.AssignmentCrossRepository;
//import com.btect.fpt.cmsbackend.repositories.CrossGradingRepository;
//import com.btect.fpt.cmsbackend.repositories.DepartmentRepository;
//import com.btect.fpt.cmsbackend.repositories.UserRepository;
//import com.btect.fpt.cmsbackend.service.SequenceGeneratorService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.stereotype.Service;
//
//import java.sql.*;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Configurable
//public class ConectorSQL {
//
//    @Autowired
//    DepartmentRepository departmentRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//
//
//    @Autowired
//    AssignmentCrossRepository assignmentCrossRepository;
//
//    public static final String URL = "jdbc:mysql://localhost:3306/moodle_production?autoReconnect=true&useSSL=false";
//    public static final String USERNAME = "root";
//    public static final String PASSWORD = "";
//
//    public static Connection getConnection() {
//        Connection con = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            //System.out.println("Conexion exitosa");
//
//        } catch (Exception e) {
//            System.out.println("Error de conexión con la base de datos");
//        }
//        return con;
//    }
//
//    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//    public List<Course> getListCourseByTeacher(String emailTeacher, Date fromDate, Date toDate) {
//      List<Course> courseList = new ArrayList<>();
//        try {
//            Connection con = null;
//            con = getConnection();
//            CallableStatement cs;
//            cs = con.prepareCall("{CALL get_list_course_by_teacher_email(?,?,?)}");
//            cs.setString(1,emailTeacher );
//            cs.setDate(2, fromDate);
//            cs.setDate(3, toDate);
////            cs.setDate(2, java.sql.Date.valueOf("2023-01-01"));
////            cs.setDate(3, java.sql.Date.valueOf("2023-05-08"));
//            cs.execute();
//            ResultSet rs2 = cs.getResultSet();
//            while (rs2.next()){
//                //course_id
//                // shortname_course
//                // fullname_course
//                // created_at
//                // update_at
//                // start_date
//                // end_date
//                // user_id
//                // username
//                // firstname
//                // lastname
//                // email
//                System.out.println(true);
//                int course_id = rs2.getInt(1);
//                String shortname = rs2.getString(2);
//                String fullName = rs2.getString(3);
//                Date createdAt = rs2.getDate(4);
//                Date updateAt = rs2.getDate(5);
//                Date startDate = rs2.getDate(6);
//                Date endDate = rs2.getDate(7);
//                int userID = rs2.getInt(8);
//                String userName = rs2.getString(9);
//                String firstName = rs2.getString(10);
//                String lastName = rs2.getString(11);
//                String email = rs2.getString(12);
//                Course course = new Course();
//                course.setCourse_id(course_id);
//                course.setFullname_course(fullName);
//                course.setShortname_course(shortname);
//                course.setUser_id(userID);
//                course.setCreated_at(createdAt);
//                course.setUpdate_at(updateAt);
//                course.setStart_date(startDate);
//                course.setEnd_date(endDate);
//                course.setUsername(userName);
//                course.setFirstname(firstName);
//                course.setLastname(lastName);
//                course.setEmail(email);
//                courseList.add(course);
//            }
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return courseList;
//    }
//    public List<Course> getAllCourseByTime( Date fromDate, Date toDate)
//    {
//
//        List<Course> courseList = new ArrayList<>();
//        try {
//            Connection con = null;
//            con = getConnection();
//            CallableStatement cs;
//            cs = con.prepareCall("{CALL get_lists_all_course_by_time(?,?)}");
////            cs.setString(1,emailTeacher );
//            cs.setDate(1, fromDate);
//            cs.setDate(2, toDate);
////            cs.setDate(2, java.sql.Date.valueOf("2023-01-01"));
////            cs.setDate(3, java.sql.Date.valueOf("2023-05-08"));
//            cs.execute();
//            ResultSet rs2 = cs.getResultSet();
//            while (rs2.next()){
//                //course_id
//                // shortname_course
//                // fullname_course
//                // created_at
//                // update_at
//                // start_date
//                // end_date
//                // user_id
//                // username
//                // firstname
//                // lastname
//                // email
//                // role_name
//                System.out.println(true);
//                int course_id = rs2.getInt(1);
//                String shortname = rs2.getString(2);
//                String fullName = rs2.getString(3);
//                Date createdAt = rs2.getDate(4);
//                Date updateAt = rs2.getDate(5);
//                Date startDate = rs2.getDate(6);
//                Date endDate = rs2.getDate(7);
//                int userID = rs2.getInt(8);
//                String userName = rs2.getString(9);
//                String firstName = rs2.getString(10);
//                String lastName = rs2.getString(11);
//                String email = rs2.getString(12);
//                Course course = new Course();
//                course.setCourse_id(course_id);
//                course.setFullname_course(fullName);
//                course.setShortname_course(shortname);
//                course.setUser_id(userID);
//                course.setCreated_at(createdAt);
//                course.setUpdate_at(updateAt);
//                course.setStart_date(startDate);
//                course.setEnd_date(endDate);
//                course.setUsername(userName);
//                course.setFirstname(firstName);
//                course.setLastname(lastName);
//                course.setEmail(email);
//
//                Optional<User> userOptional =
//                        userRepository.findByUserName(email);
//                if(userOptional.isPresent()){
//
//                    String department = userOptional.get().getDepartmentName();
//                    course.setDepartment(department);
//                }
//
//                courseList.add(course);
//            }
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return courseList;
//
//    }
//
//
//    public CourseModuleID getCourseModuleID(int courseID, int assign_ID){
//        CourseModuleID courseModuleID = new CourseModuleID();
//        try {
//            Connection con = null;
//            con = getConnection();
//            CallableStatement cs;
//            cs = con.prepareCall("{CALL get_info_course_module_by_assign_and_course_id(?,?)}");
//            cs.setInt(1, courseID);
//            cs.setInt(2, assign_ID);
//
//            cs.execute();
//            ResultSet rs2 = cs.getResultSet();
//            if (rs2.next()){
//                System.out.println(true);
//                int course_id = rs2.getInt(1);;
//                int course_module_id = rs2.getInt(2);
//                int assign_id = rs2.getInt(3);
//                courseModuleID.setCourse_module_id(course_module_id);
//                courseModuleID.setCourse_id(course_id);
//                courseModuleID.setAssign_id(assign_id);
//            }
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return courseModuleID;
//    }
//    public List<Assignment> getAllAssignmentByCourse(int courseID)
//    {
//        List<Assignment> assignmentList = new ArrayList<>();
//        try {
//            Connection con = null;
//            con = getConnection();
//            CallableStatement cs;
//            cs = con.prepareCall("{CALL get_list_assignments_by_course_id(?)}");
//            cs.setInt(1, courseID);
//            cs.execute();
//            ResultSet rs2 = cs.getResultSet();
//            while (rs2.next()){
//
//                //  course_id
//                //  shortname_course
//                //  fullname_course
//                //  assignment_id
//                //  assignment_name
//                //  due_date
//                //  cut_off_date
//                //  allow_submissions_from_date
//                //  grading_due_date
//                int course_id = rs2.getInt(1);
//                System.out.println("course_id"+course_id);
//
//                String shortname = rs2.getString(2);
//                System.out.println("shortname:"+shortname);
//
//                String fullName = rs2.getString(3);
//                System.out.println("fullName:"+fullName);
//
//                int assignmentID = rs2.getInt(4);
//                System.out.println("assignmentID:"+assignmentID);
//
//                String assignmentName = rs2.getString(5);
//                System.out.println("assignmentName:"+assignmentName);
//
//                Date due_date = rs2.getDate(6);
//                System.out.println("due_date:"+due_date);
//
//                Date cut_off_date = rs2.getDate(7);
//                System.out.println("cut_off_date:"+cut_off_date);
//
//                Date allow_submissions_from_date = rs2.getDate(8);
//                System.out.println("allow_submissions_from_date:"+allow_submissions_from_date);
//
//                Date grading_due_date = rs2.getDate(9);
//                System.out.println("grading_due_date:"+grading_due_date);
//
//
//                Assignment assignment = new Assignment();
//                assignment.setCourse_id(course_id);
//                assignment.setAssignment_id(assignmentID);
//                assignment.setAssignment_name(assignmentName);
//                assignment.setFullname_course(fullName);
//                assignment.setShortname_course(shortname);
//                assignment.setDue_date(due_date);
//                assignment.setCut_off_date(cut_off_date);
//                assignment.setAllow_submissions_from_date(allow_submissions_from_date);
//                assignment.setGrading_due_date(grading_due_date);
//                CourseModuleID courseModuleID = getCourseModuleID(course_id, assignmentID);
//                assignment.setCourse_module_id(courseModuleID.getCourse_module_id());
//                if(checkContainKey(assignmentName)){
//                    assignmentList.add(assignment);
//                }
//            }
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return assignmentList;
//
//    }
//
//    //firstname
//    // lastname
//    // email
//    // city
//    // course_name
//    // course_id
//    // assign_id
//    // assign_name
//    // shortname_role
//    // filename
//    // mimetype
//    // pathnamehash
//    // created_at_of_file
//    //
//    // updated_at_of_file
//
//
//    public List<AssignmentDetail> getAllAssignmentDetail(int courseID, int assignmentID)
//    {
//        List<AssignmentDetail> assignmentList = new ArrayList<>();
//        try {
//            Connection con = null;
//            con = getConnection();
//            CallableStatement cs;
//            cs = con.prepareCall("{CALL get_lists_file_assignment_student_by_cousre(?,?)}");
//            cs.setInt(1, courseID);
//            cs.setInt(2, assignmentID);
//            cs.execute();
//            ResultSet rs2 = cs.getResultSet();
//            while (rs2.next()){
//                //firstname
//                // lastname
//                // email
//                // city
//                // course_name
//                // course_id
//                // assign_id
//                // assign_name
//                // shortname_role
//                // filename
//                // mimetype
//                // pathnamehash
//                // created_at_of_file
//                // updated_at_of_file
//                System.out.println(true);
//                String firstname = rs2.getString(1);
//                String lastname = rs2.getString(2);
//                String email = rs2.getString(3);
//                String city = rs2.getString(4);
//                String course_name = rs2.getString(5);
//                int course_id = rs2.getInt(6);
//                int assign_id = rs2.getInt(7);
//                String assign_name = rs2.getString(8);
//                String shortname_role = rs2.getString(9);
//                String filename = rs2.getString(10);
//                String mimetype = rs2.getString(11);
//                String pathnamehash = rs2.getString(12);
//                Date created_at_of_file = rs2.getDate(13);
//                Date updated_at_of_file = rs2.getDate(14);
//
//                AssignmentDetail assignment = new AssignmentDetail();
//                assignment.setCourse_id(course_id);
//                assignment.setAssign_id(assign_id);
//                assignment.setAssign_name(assign_name);
//                assignment.setShortname_role(shortname_role);
//                assignment.setFirstname(firstname);
//                assignment.setLastname(lastname);
//                assignment.setEmail(email);
//                assignment.setCity(city);
//                assignment.setCourse_name(course_name);
//                assignment.setFilename(filename);
//                assignment.setMimetype(mimetype);
//                assignment.setPathnamehash(pathnamehash);
//                assignment.setCreated_at_of_file(created_at_of_file);
//                assignment.setUpdated_at_of_file(updated_at_of_file);
//                assignmentList.add(assignment);
//
//            }
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return assignmentList;
//    }
//
//
//
//    public List<Enrollment> getEnrollment(int courseID)
//    {
//        List<Enrollment> enrollmentList = new ArrayList<>();
//        try {
//            Connection con = null;
//            con = getConnection();
//            CallableStatement cs;
//            cs = con.prepareCall("{CALL get_enrolment_count_by_course_id(?)}");
//            cs.setInt(1, courseID);
//            cs.execute();
//            ResultSet rs2 = cs.getResultSet();
//            while (rs2.next()){
//
//                System.out.println(true);
//                int course_id = rs2.getInt(1);
//                String course_name = rs2.getString(2);
//                int enroll_num = rs2.getInt(3);
//
//                Enrollment enrollment = new Enrollment();
//                enrollment.setCourse_id(course_id);
//                enrollment.setCourse_name(course_name);
//                enrollment.setEnroled_number(enroll_num);
//
//                enrollmentList.add(enrollment);
//
//            }
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return enrollmentList;
//    }
//    //get_list_grade_student_by_course
////    public List<Grade> getListGradeInCourse(int courseID, String email)
////    {
////        List<Grade> gradeList = new ArrayList<>();
////        try {
////            Connection con = null;
////            con = getConnection();
////            CallableStatement cs;
////            cs = con.prepareCall("{CALL get_list_grade_student_by_course(?,?)}");
////            cs.setInt(1, courseID);
////            cs.setString(2, email);
////
////            cs.execute();
////            ResultSet rs2 = cs.getResultSet();
////            while (rs2.next()){
////                // userid
////                // studentid
////                // itemid
////                // courseshortname
////                // itemname
////                // itemgrademax
////                // itemaggregation
////                // finalgrade
////                System.out.println(true);
////
////                 int userid = rs2.getInt(1);;
////                 String studentid = rs2.getString(2);
////                 int itemid = rs2.getInt(3);
////                 String courseshortname = rs2.getString(4);
////                 String itemname = rs2.getString(5);
////                Double itemgrademax = rs2.getDouble(6);
////                Double itemaggregation = rs2.getDouble(7);
////                 Double finalgrade = rs2.getDouble(8);
////
////               Grade grade = new Grade();
////               grade.setCourseshortname(courseshortname);
////               grade.setFinalgrade(finalgrade);
////               grade.setItemid(itemid);
////               grade.setItemgrademax(itemgrademax);
////               grade.setItemname(itemname);
////               grade.setUserid(userid);
////               grade.setStudentid(studentid);
////               grade.setItemaggregation(itemaggregation);
////               if(finalgrade!= null && itemname!= null && checkContainKey(itemname)){
////                   gradeList.add(grade);
////               }
////            }
////            con.close();
////        } catch (Exception e) {
////            System.out.println(e);
////        }
////        return gradeList;
////    }
//
//
//    public List<Grade> getListGradeInCourseByAssigment(
//            int courseID,
//            int assignmentID,
//            String assignmentName)
//    {
//        // 1 userid
//        // 2 studentid
//        // 3 grade_id
//        // 4 grade_itemid
//        // 5 courseshortname
//        // 6 course_id
//        // 7 asmname
//        // 8 itemgrademax
//        // 9 itemaggregation
//        // 10 finalgrade
//
//       // userid
//        // studentid
//        // grade_id
//        // grade_itemid
//        // courseshortname
//        // course_id
//        // asmname
//        // itemgrademax
//        // itemaggregation
//        // finalgrade
//        List<Grade> gradeList = new ArrayList<>();
//        try {
//            Connection con = null;
//            con = getConnection();
//            CallableStatement cs;
//            CourseModuleID courseModuleID = getCourseModuleID(courseID, assignmentID);
//            cs = con.prepareCall("{CALL get_list_grade_student_by_course_final(?,?,?)}");
//            cs.setInt(1, courseID);
//            cs.setInt(2, assignmentID);
//            cs.setInt(3, courseModuleID.getCourse_module_id());
//            cs.execute();
//            ResultSet rs2 = cs.getResultSet();
//            while (rs2.next()){
//               // System.out.println(true);
//                int userid = rs2.getInt(1);;
//
//                String studentid = rs2.getString(2);
//
//                int grade_id = rs2.getInt(3);
//
//                int grade_itemid = rs2.getInt(4);
//
//                String courseshortname = rs2.getString(5);
//
//                int course_id = rs2.getInt(6);
//
//                String asmname = rs2.getString(7);
//
//                Double itemgrademax = rs2.getDouble(8);
//
//                Double itemaggregation = rs2.getDouble(9);
//
//                Double finalgrade = rs2.getDouble(10);
//
//                Grade grade = new Grade();
//                grade.setUserid(userid);
//                grade.setStudentid(studentid);
//                grade.setGrade_id(grade_id);
//                grade.setGrade_itemid(grade_itemid);
//                grade.setCourseshortname(courseshortname);
//                grade.setCourse_id(course_id);
//                grade.setAsmname(asmname);
//                grade.setItemaggregation(itemaggregation);
//                grade.setItemgrademax(itemgrademax);
//                grade.setFinalgrade(finalgrade);
//                if(finalgrade!= null && asmname!= null && checkContainKey(asmname)){
//                    if(asmname.equals(assignmentName)){
//                        gradeList.add(grade);
//                    }
//                }
//            }
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return gradeList;
//    }
//
//
//
//
//    private boolean checkContainKey(String itemname){
//        if(itemname.toLowerCase().contains("first")
//                ||itemname.toLowerCase().contains("second")
//        || itemname.toLowerCase().contains("1st")
//                ||itemname.toLowerCase().contains("2nd")){
//
//            return true;
//        }
//
//        return false;
//    }
//
//    //get_info_course_module_by_assign_and_course_id
//
//
//    @Autowired
//    CrossGradingRepository crossGradingRepository;
//
//    @Autowired
//    SequenceGeneratorService sequenceGeneratorService;
//
//    public   List<AssignmentCross> setupAssignmentCross(Date fromDate, Date toDate){
//
//        List<AssignmentCross> assignmentCrossList = new ArrayList<>();
//        try {
//            Connection con = null;
//            con = getConnection();
//            CallableStatement cs;
//            cs = con.prepareCall("{CALL get_lists_all_course_by_time(?,?)}");
//            cs.setDate(1, fromDate);
//            cs.setDate(2, toDate);
//            cs.execute();
//            ResultSet rs2 = cs.getResultSet();
//            while (rs2.next()){
//                int course_id = rs2.getInt(1);
//                String shortname = rs2.getString(2);
//                String fullName = rs2.getString(3);
//                Date createdAt = rs2.getDate(4);
//                Date updateAt = rs2.getDate(5);
//                Date startDate = rs2.getDate(6);
//                Date endDate = rs2.getDate(7);
//                int userID = rs2.getInt(8);
//                String userName = rs2.getString(9);
//                String firstName = rs2.getString(10);
//                String lastName = rs2.getString(11);
//                String email = rs2.getString(12);
//                Course course = new Course();
//                course.setCourse_id(course_id);
//                course.setFullname_course(fullName);
//                course.setShortname_course(shortname);
//                course.setUser_id(userID);
//                course.setCreated_at(createdAt);
//                course.setUpdate_at(updateAt);
//                course.setStart_date(startDate);
//                course.setEnd_date(endDate);
//                course.setUsername(userName);
//                course.setFirstname(firstName);
//                course.setLastname(lastName);
//                course.setEmail(email);
//                List<Assignment> assignmentList = getAllAssignmentByCourse(course_id);
//                System.out.println("assignmentList:"+assignmentList.size());
//                int index1=0;
//                int index2 = 1;
//                int index3 = 2;
//                boolean check = false;
//                int length = assignmentList.size();
//                if(length>0)
//                for(int i =0;i< length;i++)
//                {
//                     List<Grade> gradeList = new ArrayList<Grade>();
//                     try {
//                         gradeList = getListGradeInCourseByAssigment
//                                 (assignmentList.get(i).getCourse_id(),
//                                         assignmentList.get(i).getAssignment_id(),
//                                         assignmentList.get(i).getAssignment_name());
//                     }catch (Exception ex){
//
//
//                     }
//
//
//                    System.out.println("gradeList:"+ gradeList.size());
//                    if(gradeList.size()>=3){
// //                       int length = gradeList.size();
////                        if(!check){
////
////                        }
////                        do{
////                            index1 = new Random().nextInt(length);
////                            index2 = index1 + new Random().nextInt(length);
////                            index3 = index2+ new Random().nextInt(length);
////                        } while(index3== index1
////                                || index3== index2
////                                || index1 == index2
////                                || index1>=length
////                                || index2>= length
////                                || index3>= length);
//                        System.out.println("index1:"+ index1);
//                        System.out.println("index2:"+ index2);
//                        System.out.println("index3:"+ index3);
////                        do{
////                            index1 = new Random().nextInt(length);
////                            index2 = index1 + new Random().nextInt(length);
////                            index3 = index2+ new Random().nextInt(length);
////                        } while(index3== index1
////                                || index3== index2
////                                || index1 == index2
////                                || index1>=length
////                                || index2>= length
////                                || index3>= length);
////                        System.out.println("indexxx1:"+ index1);
////                        System.out.println("indexxx2:"+ index2);
////                        System.out.println("indexxx3:"+ index3);
//                        check = true;
//
//                        try{
//                            Grade grade1 = gradeList.get(index1);
//
//                            AssignmentCross assignmentCross = new AssignmentCross();
//                            assignmentCross.setCourseshortname(shortname);
//                            assignmentCross.setCourse_id(course_id);
//                            assignmentCross.setFullname_course(fullName);
//                            assignmentCross.setUsername(userName);
//                            assignmentCross.setEmail(email);
//                            assignmentCross.setStudentid(grade1.getStudentid());
//                            assignmentCross.setAsmname(grade1.getAsmname());
//                            assignmentCross.setFinalgrade(grade1.getFinalgrade());
//                            assignmentCross.setGradeId(grade1.getGrade_id());
//                            assignmentCross.setCreatedDate(createdAt);
//                            assignmentCrossList.add(assignmentCross);
//                        }catch (Exception ex){
//
//                        }
//
//                        try{
//                            Grade grade2 = gradeList.get(index2);
//                            AssignmentCross assignmentCross2 = new AssignmentCross();
//                            assignmentCross2.setCourseshortname(shortname);
//                            assignmentCross2.setCourse_id(course_id);
//                            assignmentCross2.setFullname_course(fullName);
//                            assignmentCross2.setUsername(userName);
//                            assignmentCross2.setCreatedDate(createdAt);
//                            assignmentCross2.setEmail(email);
//                            assignmentCross2.setStudentid(grade2.getStudentid());
//                            assignmentCross2.setAsmname(grade2.getAsmname());
//                            assignmentCross2.setFinalgrade(grade2.getFinalgrade());
//                            assignmentCross2.setGradeId(grade2.getGrade_id());
//                            assignmentCrossList.add(assignmentCross2);
//                        }catch (Exception ex){
//
//                        }
//
//
//                        try{
//                            Grade grade3 = gradeList.get(index3);
//
//                            AssignmentCross assignmentCross3 = new AssignmentCross();
//                            assignmentCross3.setCourseshortname(shortname);
//                            assignmentCross3.setCourse_id(course_id);
//                            assignmentCross3.setFullname_course(fullName);
//                            assignmentCross3.setUsername(userName);
//                            assignmentCross3.setEmail(email);
//                            assignmentCross3.setCreatedDate(createdAt);
//                            assignmentCross3.setStudentid(grade3.getStudentid());
//                            assignmentCross3.setAsmname(grade3.getAsmname());
//                            assignmentCross3.setFinalgrade(grade3.getFinalgrade());
//                            assignmentCross3.setGradeId(grade3.getGrade_id());
//                            assignmentCrossList.add(assignmentCross3);
//                        }catch (Exception exception){
//
//                        }
//
//                    }else {
//                        continue;
//                    }
//                }
//            }
//
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return assignmentCrossList;
//
//
//    }
//
//}
