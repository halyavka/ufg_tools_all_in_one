
Scenario: check work Increment/Decrement plugin in Survey

Given login as tomepJB

When create survey with all types of questions Increment/Decrement Number from existing survey
Then survey is added and SID not null

When set allow multiple responses option
When set new page after question Q4
When go to plugin page from question list page
When add plugin Increment/Decrement Number with parameters Q1 and category = 6

Then check text of plugin Increment/Decrement Number with parameters Q1

When get voting master Url
When go to voting
Then check work of plugin Increment/Decrement Number with parameters Q1

When go to plugin page from voting page
When delete plugin
When add plugin Increment/Decrement Number with parameters Q11 and category = 6

Then check text of plugin Increment/Decrement Number with parameters Q11

When get voting master Url
When go to voting
Then check work of plugin Increment/Decrement Number with parameters Q11

When go to plugin page from voting page
When deactivate/activate plugin Increment/Decrement Number
When get question page from plugin page
When get voting master Url
When go to voting
Then check work of plugin Increment/Decrement Number with parameter = Q11 if it is not active

When go to plugin page from voting page
When deactivate/activate plugin Increment/Decrement Number