
Create all supported type of questions in Survey, Form, Linear Form

GivenStories: stories/Survey/SurveyOperations/ActiveSurveys/New/Survey/CreateSurvey.story

Scenario:  Create Pick One or Other
When Insert New Question Pick One or Other with system answer options and check Other
Then Question Pick One or Other added

Scenario:  Create Pick One with Comment
When Insert New Question Pick One with Comment with some answer and specify Comment field
Then Question Pick One with Comment added

Scenario:  Create Check All that Apply
When Insert New Question Check All that Apply with some answer and check Other
Then Question Check All that Apply added

Scenario:  Create Drop Down Box
When Insert New Question Drop Down Box with system answer options
Then Question Drop Down Box added

Scenario:  Create List Box
When Insert New Question List Box with some answer options
Then Question List Box added

Scenario:  Create Single Line
When Insert New Question Single Line with some answer options
Then Question Single Line added

Scenario:  Create Multi Line
When Insert New Question Multi Line 
Then Question Multi Line added

Scenario:  Create Compare One against Another
When Insert New Question Compare One against Another with some answer options
Then Question Compare One against Another added

Scenario:  Create Rate Different
When Insert New Question Rate Different with some answer options and system scale
Then Question Rate Different added

Scenario:  Create 3D Matrix
When Insert New Question 3D Matrix with some answer options
Then Question 3D Matrix added

Scenario:  Create Numeric Allocation
When Insert New Question Numeric Allocation with some answer options
Then Question Numeric Allocation added

Scenario:  Create Lookup
Given We have Data Model
When Insert New Question Lookup
Then Question Lookup added

Scenario:  Create File Upload
When Insert New Question File Upload with some answer options
Then Question File Upload added

Scenario:  Create Section Header
When Insert New Question Section Header
Then Question Section Header added

