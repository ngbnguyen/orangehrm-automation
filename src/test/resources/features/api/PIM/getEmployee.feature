Feature: PIM API
  @pim @get_employee @api
  Scenario: User get employees list in dashboard
    When user calls api via json file "data/api/PIM/getEmployee.json"
    Then response status should be 200
    Then verify response data is not null
    Then response field should not be null: "empNumber"
    Then response field should not be null: "lastName"
    Then response field should not be null: "firstName"