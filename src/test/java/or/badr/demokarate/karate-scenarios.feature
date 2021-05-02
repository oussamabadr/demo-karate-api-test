Feature: Testing a REST API with Karate

Background:
  * def localhost = 'http://localhost:9090'

Scenario: Should return OK for a valid GET
Given url localhost + '/user/get'
When method GET
Then status 200

Scenario: Should return Bob for a valid GET with exact fields
Given url localhost + '/user/get'
When method GET
Then status 200
And match $ == {id:"#notnull",name:"Bob Jack"}

Scenario: Should return body with non null ID for valid POST
Given url localhost + '/user/create'
And request { id: '9876' , name: 'Fernandez Smith'}
When method POST
Then status 200
And match $ contains {id:"#notnull"}