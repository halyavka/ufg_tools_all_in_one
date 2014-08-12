Scenario: check work Page Selector plugin in Survey

Given login as 	tomepJB
Given survey Walmart 2014 Test Survey Online is exist

When get voting master Url
When go to voting

When select page 1 for page selector plugin
When answer 1 on single line question Q3.A1,Q3.A2,Q3.A3 with +/- buttons for increment/decrement plugin
When answer on pick one question Q5.A1,Q25.A1
When select page 2 for page selector plugin
When answer 3 on single line question Q59.A1,Q59.A2,Q60.A1,Q60.A2,Q60.A3 with +/- buttons for increment/decrement plugin
When select page 3 for page selector plugin
When take/upload a file Dog.GIF for questions Q62.A1
When select page 4 for page selector plugin
When click on "Submit" button

Then alert: Please answer the following question:  D-SPK end cap/standalone dis #1 in Primary Loc


