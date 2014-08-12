Scenario: check work Toggle plugin in Survey

Given login as 	tomepJB
Given survey Walmart 2014 Test Survey is exist
Given portal for CM TestContactManager with contact walmart is set up
Given survey Walmart 2014 Test Survey is published on portal TestContactManager
Given login to portal for CM TestContactManager via WA Offline App for user with login walmart and password 123
And click create response button for survey

When answer 1 on single line question Q3.A1,Q3.A2,Q3.A3 with +/- buttons for increment/decrement plugin
When answer Yes to all pick one questions on page
When answer on pick one question Q25.A2
When click down arrow for question with id = CMT_10 to open the comment field
Then the single line field is opened under the question 26
When select page 2 for page selector plugin
When answer 1 on single line question Q59.A1,Q59.A2,Q60.A1,Q60.A2,Q60.A3 with +/- buttons for increment/decrement plugin
When select page 4 for page selector plugin
When click on "Submit" button

Then alert: Please answer the following question: Comments (D-SPK core brands in stock on core displays)...
