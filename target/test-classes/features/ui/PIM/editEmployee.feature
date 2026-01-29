Feature: PIM UI

  @edit_employee @ui
  Scenario Outline: Edit Employee Information
    Given Login as Admin
    And User navigates to page: "pim"
    When User clicks on item tab: "Add Employee"
    And User fills in all create new employee's information "<firstname>" "<middlename>" "<lastname>" "<avatarPath>"
    And User fills in Create Login Details information "<password>" "<confirmPassword>"
    And Get new employee id
    Then Employee ID is displayed
    When User clicks on Save button
    And Wait for title visible: "Personal Details"
    Then Validate employee name is correct "<firstname>" "<lastname>"
    And Validate employee ID is correct

    When User clicks on item tab: "Employee List"
    And User searches employee with employee ID
    When User edits Employee - Driver's License Number
    And User clicks on Save button
    Then Validate Driver's License Number is updated correctly
    Examples:
      | firstname | middlename | lastname     | avatarPath                        | password | confirmPassword |
      | EditFName | MName      | EditLName    | src/test/resources/avatar_img.png | Admin123 | Admin123        |