
GivenStories: stories/Survey/Surveys/Survey_Survey_Offline_CustomForm\Launch/DistributionMethod/PublishOnParticipantPortal/Publish_on_Participant_Portal.story

Scenario:  A scenario is a collection of executable steps of different type

Given run application on Device <device>
When Login to Offline Portal
When Start Synchronisation process
Then Published Survey is present on Offline Portal
Examples:
|device
|Android_4.1.2