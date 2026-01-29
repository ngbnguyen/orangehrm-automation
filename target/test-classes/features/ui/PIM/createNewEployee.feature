Feature: PIM UI

    @create_employee @ui
    Scenario Outline: Create new employee and create login details
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
        Examples:
            | firstname | middlename | lastname | avatarPath                        | password | confirmPassword |
            | FName     | MName      | LName    | src/test/resources/avatar_img.png | Admin123 | Admin123        |
