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
    
    public StudentValidator(StudentDAO dao) {
        this.idValidator = new IdValidator(dao);
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
    
    public String getValidationErrors(StudentInfo student) {
        StringBuilder errors = new StringBuilder();

        if (!idValidator.validate(student.getId())) {
            errors.append("ID must be an 8 digit integer.\n");
        }
        if (!nameValidator.validate(student.getName())) {
            errors.append("Name can only contain letters and spaces.\n");
        }
        if (!degreeValidator.validate(student.getDegree())) {
            errors.append("Degree can only contain letters and spaces.\n");
        }
        if (!gradeValidator.validate(student.getGrade())) {
            errors.append("Grade must be a valid letter grade.\n");
        }

        return errors.toString().trim();
    }
    
}
