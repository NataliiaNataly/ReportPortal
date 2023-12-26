#RULES: 
All code (with the below exceptions) should be reviewed by at least one other engineer 
before it is merged into the main branch of a repository.

Exceptions:
Config changes that only affect development and staging environments do not require a code review.
Changes that are purely lint fixes do not require a code review.

#THE CODE REVIEW PROCESS
One of the main limitations of the GitHub review process is knowing whose turn it is. 
In order to avoid reviews stagnating we should be explicit about when a step has finished.

1) As a reviewer, when you have finished making comments let the submitter know you are 
done with your pass and are expecting followups:
"Ok, @teammember, address those comments, and I'll take another look"
2) As a submitter, let your reviewers know when you have addressed all the comments and 
pushed your changes:
"Hey @teammember, addressed your comments, how does this look?"
3) It is the responsibility of the submitter to keep the review on track and make sure 
it gets merged. Don't hesitate to ping the review thread if you are waiting for a long time:
"Ping @teammember, this is ready for another look"
4) As a reviewer, if your comments are simple or superficial make sure to let the submitter 
know they can merge without needing another round:
"It looks OK to me after those comments have been resolved"



