
Scenario: check work Page Selector plugin in Survey

Given login as tomepJB

When create survey with all types of questions Page Selector from existing survey
Then survey is added and SID not null

When set allow multiple responses option
When set new page after question Q3
When set new page after question Q5
When set new page after question Q8
When set new page after question Q10
When go to plugin page from question list page
When add plugin Page Selector with parameters Header;false;1 S1;4 S4;9 S9 and category = 6

Then check text of plugin Page Selector with parameters Header;false;1 S1;4 S4;9 S9

When get voting master Url
When go to voting
Then check work of plugin Page Selector with parameters Header;false;1 S1;4 S4;9 S9 (case1)

When go to plugin page from voting page
When delete plugin
When add plugin Page Selector with parameters Header;false;4 S4;6 S6;9 S9 and category = 6

Then check text of plugin Page Selector with parameters Header;false;4 S4;6 S6;9 S9

When get voting master Url
When go to voting
Then check work of plugin Page Selector with parameters Header;false;4 S4;6 S6;9 S9 (case2)

When go to plugin page from voting page
When deactivate/activate plugin Page Selector
When get question page from plugin page
When get voting master Url
When go to voting
Then check work of plugin Tab Selector with parameter = Header;false;4 S4;6 S6;9 S9 if it is not active

When go to plugin page from voting page
When deactivate/activate plugin Page Selector