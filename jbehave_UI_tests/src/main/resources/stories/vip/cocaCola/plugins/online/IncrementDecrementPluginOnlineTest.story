Scenario: check work Increment/Decrement plugin in Survey

Given login as 	tomepJB
Given survey Walmart 2014 Test Survey Online is exist

When get voting master Url
When go to voting
When select page 1 for page selector plugin
When answer -3 on single line question Q3.A1 with +/- buttons for increment/decrement plugin
Then value of question Q3.A1 = 0

When answer -3 on single line question Q3.A3 by on-screen keyboard for increment/decrement plugin
Then alert: The number you have entered is outside the possible range.More than 0.

When answer 3 on single line question Q3.A1 with +/- buttons for increment/decrement plugin
When answer 3 on single line question Q3.A2 by on-screen keyboard for increment/decrement plugin
When answer 3 on single line question Q3.A3 with +/- buttons for increment/decrement plugin
When select page 4 for page selector plugin
Then the resulted text is Conformity score: 0 %Conformity (weighted) score: 0 %D-KO SPK Displays => than Pepsi: 50 %E-KO to Pepsi >=X:1 (share):Sparkling Percentage KO:Sparkling Percentage PC:Sparkling Percentage Other:Powerade Percentage:Gatorade Percentage:Water Percentage KO:Water Percentage PC:Water Percentage Other: in question Q75

When select page 2 for page selector plugin
When select page 4 for page selector plugin
Then the resulted text is Conformity score: 0 %Conformity (weighted) score: 0 %D-KO SPK Displays => than Pepsi: 50 %E-KO to Pepsi >=X:1 (share):Sparkling Percentage KO: 33.3 %Sparkling Percentage PC: 33.3 %Sparkling Percentage Other: 33.3 %Powerade Percentage:Gatorade Percentage:Water Percentage KO:Water Percentage PC:Water Percentage Other: in question Q75
