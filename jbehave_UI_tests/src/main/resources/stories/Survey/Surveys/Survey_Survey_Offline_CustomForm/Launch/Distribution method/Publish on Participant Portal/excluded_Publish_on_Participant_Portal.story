
Scenario:  Publishing Survey to Default and NotDefaulf Contact manager

Given We have <survey_type> with all type questions
When Publish to Participant Portal with <cm_type> contact manager
Then Survey has been success published to Participant Portal

Examples:
|survey_type  |cm_type
|survey       |default
|form         |notDefault