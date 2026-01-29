Feature: PIM API
  @pim @create_employee @api
  Scenario: Create new employee with valid payload
    Given Generate random 4 digits number and store as "employeeId"
    When user calls api via json file "data/api/Request/PIM/createEmployee.json"
    Then response status should be 200
    Then verify response data is not null
    And response field should be null: "terminationId"
    And response field "data.employeeId" should be "employeeId"
    When store "data.empNumber" from response to list "ids"
    And user calls api via json file "data/api/Request/PIM/deleteEmployee.json"
    Then response status should be 200
    And response field "data" should be "ids"

