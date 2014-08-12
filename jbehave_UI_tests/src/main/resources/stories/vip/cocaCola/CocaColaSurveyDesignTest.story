
Scenario: check work custon scripts and CSS in Survey

Given login as ivan

When create new survey CocaColaCheckDesign from existing survey
Then survey is added and SID not null