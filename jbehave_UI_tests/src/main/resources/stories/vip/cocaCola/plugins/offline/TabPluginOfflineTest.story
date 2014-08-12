
Scenario: check work Page Selector plugin in Survey

GivenStories: stories/LoginToAccount.story#{1}

Given survey Walmart 2014 Test Survey is exist
Given portal for CM TestContactManager with contact walmart is set up
Given survey Walmart 2014 Test Survey is published on portal TestContactManager
Given login to portal for CM TestContactManager via WA Offline App for user with login walmart and password 123
And click create response button for survey

When select page 1 for page selector plugin
When answer 1 on single line question Q3.A1,Q3.A2,Q3.A3 with +/- buttons for increment/decrement plugin
When answer on pick one question Q5.A1,Q25.A1
When select page 2 for page selector plugin
When answer 3 on single line question Q59.A1,Q59.A2,Q60.A1,Q60.A2,Q60.A3 with +/- buttons for increment/decrement plugin
When select page 3 for page selector plugin
When take/upload a file "" for question ""
When select page 4 for page selector plugin
When click on "Submit" button

Then alert: Please answer the following question: E-KO to Pepsi >=X:1  (share)*  ...


