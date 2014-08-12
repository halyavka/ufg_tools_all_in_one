
Scenario: create simple logic for SingleLine with different Date Format value in Survey

Given login as ivan

When create new survey <surveyName> from existing survey
Then survey is added and SID not null

When edit question Q6 format type: Date

When set allow multiple responses option

When open actions window
When create new branching logic with value: after Q6 If Q6 A1 <logicalCondition> with value <logicValue> go to Q11
When create new branching logic with value: after Q6 If Q6 A2 <logicalCondition> with value <logicValue> go to Q9
When create new branching logic with value: after Q6 If Q6 A3 <logicalCondition> with value <logicValue> go to Q8
When create new branching logic with value: after Q6 If Q6 A4 <logicalCondition> with value <logicValue> go to Q10
When close actions window

Then logical expression for Q6 visible on question list page and checked 'New page' after this expression

When get voting master Url
When go to voting

When voting: select Q6 A1 and type value1: <value1>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q7
When click 'Back' button on VotingPage
When voting: select Q6 A1 and type value2: <value2>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q7
When click 'Back' button on VotingPage
When voting: select Q6 A1 and type value3: <value3>
When click 'Next' button on VotingPage
Then finish check logic after voting: go to Q11

When go to voting

When voting: select Q6 A2 and type value4: <value4>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q7
When click 'Back' button on VotingPage
When voting: select Q6 A2 and type value5: <value5>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q7
When click 'Back' button on VotingPage
When voting: select Q6 A2 and type value6: <value6>
When click 'Next' button on VotingPage
Then finish check logic after voting: go to Q9

When go to voting

When voting: select Q6 A3 and type value7: <value7>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q7
When click 'Back' button on VotingPage
When voting: select Q6 A3 and type value8: <value8>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q7
When click 'Back' button on VotingPage
When voting: select Q6 A3 and type value9: <value9>
When click 'Next' button on VotingPage
Then finish check logic after voting: go to Q8

When go to voting

When voting: select Q6 A4 and type value10: <value10>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q7
When click 'Back' button on VotingPage
When voting: select Q6 A4 and type value11: <value11>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q7
When click 'Back' button on VotingPage
When voting: select Q6 A4 and type value12: <value12>
When click 'Next' button on VotingPage
Then finish check logic after voting: go to Q10

When delete survey

Examples:
|surveyName                       |logicalCondition           |logicValue |value1     |value2     |value3     |value4     |value5     |value6     |value7     |value8     |value9     |value10    |value11    |value12
|DateLogicContain(s)              |contain(s)                 |2013-08    |22/07/2013 |24/09/2013 |23/08/2013 |01/22/2013 |10/08/2013 |08/23/2013 |06-08-2013 |09-24-2013 |08-23-2013 |2013-07-22 |2013-09-08 |2013-08-23
|DateLogicDo(es)NotContain        |do(es) not contain         |2013-08-23 |23/08/2013 |23/08/2013 |24/08/2013 |08/23/2013 |08/23/2013 |08/22/2013 |08-23-2013 |08-23-2013 |08-03-2013 |2013-08-23 |2013-08-23 |2013-08-02
|DateLogicEqual(s)To              |equal(s) to                |2013-11-01 |01/12/2013 |23/08/2013 |01/11/2013 |01/01/2013 |11/02/2013 |11/01/2013 |11-23-2013 |11-11-2013 |11-01-2013 |2013-11-21 |2013-01-11 |2013-11-01
|DateLogicDo(es)NotEqualTo        |do(es) not equal to        |2013-08-08 |08/08/2013 |08/08/2013 |07/08/2013 |08/08/2013 |08/08/2013 |08/11/2013 |08-08-2013 |08-08-2013 |08-22-2013 |2013-08-08 |2013-08-08 |2013-08-02
|DateLogicContain(s)(RegEx)       |contain(s) (RegEx)         |2013-08-23 |02/08/2013 |22/08/2013 |23/08/2013 |08/22/2013 |08/24/2013 |08/23/2013 |08-22-2013 |08-24-2013 |08-23-2013 |2013-08-24 |2013-08-22 |2013-08-23
|DateLogicDo(es)NotContain(RegEx) |do(es) not contain (RegEx) |2013       |23/08/2013 |23/08/2013 |24/08/2012 |08/23/2013 |12/23/2013 |08/22/1996 |08-23-2013 |08-23-2013 |08-03-1978 |2013-12-23 |2013-09-23 |2000-08-02
|DateLogicEqual(s)To(RegEx)       |equal(s) to (RegEx)        |2000-01-10 |23/08/2013 |10/08/2000 |10/01/2000 |01/10/1900 |01/10/2010 |01/10/2000 |01-01-2000 |01-10-2011 |01-10-2000 |2011-11-11 |2012-12-12 |2000-01-10
|DateLogicDo(es)NotEqualTo(RegEx) |do(es) not equal to (RegEx)|2013-08-23 |23/08/2013 |23/08/2013 |24/08/2013 |08/23/2013 |08/23/2013 |08/22/2013 |08-23-2013 |08-23-2013 |08-03-2013 |2013-08-23 |2013-08-23 |2013-08-02
|DateLogicLessThan                |less than (date)                 |2013-08-01 |01/08/2013 |02/08/2013 |30/07/2013 |06/13/2014 |08/01/2013 |06/22/2013 |08-23-2013 |08-23-2023 |08-03-2012 |2013-12-23 |2013-11-23 |2013-02-02
|DateLogicLessThanOrEqualTo       |less than or equal to (date)     |1990-12-12 |23/12/1990 |13/12/1990 |12/12/1990 |12/23/2031 |08/23/2015 |12/12/1990 |08-23-2014 |12-12-2013 |12-11-1990 |2012-11-12 |2013-12-12 |1990-11-12
|DateLogicGreaterThan             |greater than (date)              |2000-11-11 |11/11/2000 |11/11/1999 |12/11/2000 |08/23/1930 |08/23/1998 |11/22/2011 |10-23-2000 |11-11-2000 |12-03-2000 |2000-08-23 |2000-08-13 |2000-11-12
|DateLogicGreaterThanOrEqualTo    |greater than or equal to (date)  |2013-12-31 |23/08/2013 |23/12/2013 |31/12/2013 |08/23/2013 |12/12/2013 |01/01/2014 |08-23-2013 |08-23-2013 |12-31-2013 |2013-08-23 |2013-08-23 |2116-12-31