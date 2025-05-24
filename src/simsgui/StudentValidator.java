/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

/**
 *
 * @author marku
 */
public class StudentValidator extends Validator<StudentInfo>{
    private final IdValidator idValidator;
    private final GradeValidator gradeValidator;
    private final NameValidator nameValidator;
    private final DegreeValidator degreeValidator;
    
    public StudentValidator(StudentDAO studentDAO) {
        this.idValidator = new IdValidator(studentDAO);
        this.gradeValidator = new GradeValidator();
        this.nameValidator = new NameValidator();
        this.degreeValidator = new DegreeValidator();
    }
    
    @Override
    public boolean validate(StudentInfo student) {
        return idValidator.validate(student.getId()) 
                & nameValidator.validate(student.getName())
                & degreeValidator.validate(student.getDegree())
                & gradeValidator.validate(student.getGrade());
    }
    
}
