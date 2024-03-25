# Interview Panel Application

![Login Screen](https://imgur.com/mZhgxo6)
![Adding Interview Panels](https://imgur.com/XSpx4EQ)
![Adding Candidates](https://imgur.com/T1aCnLT)
![View Panel](https://imgur.com/sFFvCgx)
![Viewing after terminating interview](https://imgur.com/xr2G3kV)
![Changing Status of Candidate](https://imgur.com/wcDvDd5)

## Type of Application
B2B (Business-to-Business)

## Target Users
Administrators

## Development Time
3 days

## Tech Stack
Java

## Features visible to Admin (Phase 1)
- Login
- Add interviewer(s) and create a new interview session
- Add candidate to queue
- Remove candidate from queue
- Mark a candidate that has already been interviewed
- Remove Interviewer
- Terminate all interview sessions
- Log out

## Features to be Automated
- If there are multiple interviewers, add a new candidate to the interview panel with less number of candidates in the queue
- Assignments of ID

## Classes
### Candidate class:
- candidateId: int or UUID
- candidateName: String
- candidateDOB: Date
- positionForInterviewing: String
- interviewStatus: enum InterviewStatus
- candidateResume: blob

### InterviewStatus enum:
- WAITING_FOR_INTERVIEW
- BEING_INTERVIEWED
- UNDER_REVIEW
- SELECTED
- NOT_SELECTED

### Admin class:
- adminId: int or UUID
- adminName: String

### Interviewer class:
- employeeId: int or UUID
- employeeName: String
- employeeDesignation: String
- yearsOfExperience: int
- employeeDepartment: String

### InterviewPanel class:
- interviewPanelId: int or UUID
- interviewerDetails: Interviewer
- jobDescription: String
- candidateQueue: Queue\<Candidate\>
- createdAt: Date
- endTime: Date

## Interview Panel Options
1. Add new Interview Panel
2. Terminate Current Interview In a panel
3. Clear an Interview Panel
4. Clear all queues
5. Delete interview panel
6. View All Interview Panels

## Candidate Options
1. Add Candidate to Panel
2. Remove Candidate
3. Change result of candidate

## Admin Options
1. Add New Admin
2. Remove Admin
3. Update Admin
