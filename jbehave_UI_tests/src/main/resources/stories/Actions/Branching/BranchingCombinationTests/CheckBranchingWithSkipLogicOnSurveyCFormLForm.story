
Scenario: check branching logic with skip logic (GoTo) on LinearForm

Given login as ivan
When create object linear_form with name Logic_LinearForm from scratch with based questions and set new page after questions 2,3,4,5
Then survey is added and SID not null

When create skip logic: after question Q3 go to Page 5.
When create skip logic: after question Q4 go to Page 5.

When open actions window
When create new branching (advanced) logic: after question Page 1 type logic expression ( Q1.A1 AND Q2.A1 ) go to Page 3
When create new branching (advanced) logic: after question Page 1 type logic expression ( Q1.A2 AND Q2.A2 ) go to Page 4
When close actions window

When get voting master Url
When go to voting

When voting (RC): select Q1 A1
When voting (RC): select Q2 A1
When click 'Next' button on VotingPage
When voting (DropDown): select Q4 A3
When click 'Next' button on VotingPage
When voting: select Q6 A2 and type value: single_line_answer_2
When click on 'Submit' button

When open completed RBR page

Then answer Q1.A1 is checked true
Then answer Q2.A1 is checked true
Then answer Q4.A3 is selected true
Then answer Q6.A2 text is equal single_line_answer_2

When close RBR page
When delete survey


Scenario: check branching logic with skip logic (GoTo) on Survey

Given login as ivan
When create object survey with name Logic_Survey from scratch with based questions and set new page after questions 2,3,4,5
Then survey is added and SID not null

When create skip logic: after question Q3 go to Q6
When create skip logic: after question Q4 go to Q6

When open actions window
When create new branching (advanced) logic: after question Q1 type logic expression ( Q1.A1 AND Q2.A1 ) go to Q4
When create new branching (advanced) logic: after question Q1 type logic expression ( Q1.A2 AND Q2.A2 ) go to Q5
When close actions window

When get voting master Url
When go to voting

When voting (RC): select Q1 A1
When voting (RC): select Q2 A1
When click 'Next' button on VotingPage
When voting (DropDown): select Q4 A3
When click 'Next' button on VotingPage
When voting: select Q6 A2 and type value: single_line_answer_2
When click on 'Submit' button

When open completed RBR page

Then answer Q1.A1 is checked true
Then answer Q2.A1 is checked true
Then answer Q4.A3 is selected true
Then answer Q6.A2 text is equal single_line_answer_2

When close RBR page
When delete survey


Scenario: check branching logic with skip logic (GoTo) on CustomForm

Given login as ivan
When create object custom_form with name Logic_CustomForm from scratch with based questions and set new page after questions 2,3,4,5
Then survey is added and SID not null

When create skip logic: after question Q3 go to Page 5.
When create skip logic: after question Q4 go to Page 5.

When open actions window
When create new branching (advanced) logic: after question Page 1 type logic expression ( Q1.A1 AND Q2.A1 ) go to Page 3
When create new branching (advanced) logic: after question Page 1 type logic expression ( Q1.A2 AND Q2.A2 ) go to Page 4
When close actions window

When get voting master Url
When go to voting

When voting (RC): select Q1 A1
When voting (RC): select Q2 A1
When click 'Next' button on VotingPage
When voting (DropDown): select Q4 A3
When click 'Next' button on VotingPage
When voting: select Q6 A2 and type value: single_line_answer_2
When click on 'Submit' button

When open completed RBR page

Then answer Q1.A1 is checked true
Then answer Q2.A1 is checked true
Then answer Q4.A3 is selected true
Then answer Q6.A2 text is equal single_line_answer_2

When close RBR page
When delete survey
