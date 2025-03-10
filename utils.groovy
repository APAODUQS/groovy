def WORKFLOW_URL = 'https://prod-108.westeurope.logic.azure.com:443/workflows/4'

def teamsNotification(String appName) {
    def workflowUrl = WORKFLOW_URL
    def prTitle = env.CHANGE_TITLE ?: "N/A"
    def prNumber = env.CHANGE_ID ?: "N/A"
    def buildStatus = currentBuild.currentResult ?: "N/A"
    def prAuthor = env.CHANGE_AUTHOR_DISPLAY_NAME ?: env.CHANGE_AUTHOR ?: "N/A"
    def buildStartTime = new Date(currentBuild.startTimeInMillis + currentBuild.duration).format("yyyy-MM-dd HH:mm:ss")
    def imageUrl = "https://www.jenkins.io/images/logos/jenkins/jenkins.png"
    def bldStatus = "Jenkins Build SUCCESS"
    def bldStatusColor = "Good"
    def stageStatus = "All Stages Passed"

    if (buildStatus == 'FAILURE') {
        imageUrl = "https://www.jenkins.io/images/logos/fire/fire.png"
        bldStatus = "Jenkins Build FAIL"
        bldStatusColor = "warning"
        stageStatus = "${currentStage}"
    }

    def payload = """
    {
        "type": "message",
        "attachments": [
            {
                "contentType": "application/vnd.microsoft.card.adaptive",
                "content": {
                    "\$schema": "http://adaptivecards.io/schemas/adaptive-card.json",
                    "type": "AdaptiveCard",
                    "version": "1.6",
                    "body": [
                        {
                            "type": "TextBlock",
                            "size": "Medium",
                            "weight": "Bolder",
                            "text": "Build Notification"
                        },
                        {
                            "type": "ColumnSet",
                            "columns": [
                                {
                                    "type": "Column",
                                    "items": [
                                        {
                                            "type": "Image",
                                            "url": "${imageUrl}",
                                            "altText": "Jenkins Logo",
                                            "size": "Medium",
                                            "height": "50px",
                                            "width": "48px"
                                        }
                                    ],
                                    "width": "auto"
                                },
                                {
                                    "type": "Column",
                                    "items": [
                                        {
                                            "type": "TextBlock",
                                            "weight": "Bolder",
                                            "text": "${bldStatus}",
                                            "color": "${bldStatusColor}",
                                            "wrap": true,
                                            "style": "heading",
                                            "size": "ExtraLarge",
                                            "isSubtle": true
                                        },
                                        {
                                            "type": "TextBlock",
                                            "spacing": "None",
                                            "text": "Build end time: ${buildStartTime}",
                                            "wrap": true,
                                            "isSubtle": true
                                        }
                                    ],
                                    "width": "auto",
                                    "verticalContentAlignment": "Center"
                                }
                            ]
                        },
                        {
                            "type": "FactSet",
                            "separator": true,
                            "facts": [
                                {
                                    "title": "App Name:",
                                    "value": "${appName}"
                                },
                                {
                                    "title": "Stage:",
                                    "value": "${stageStatus}"
                                },
                                {
                                    "title": "Job Name:",
                                    "value": "${env.JOB_BASE_NAME}"
                                },
                                {
                                    "title": "Build Number:",
                                    "value": "${env.BUILD_NUMBER}"
                                },
                                {
                                    "title": "PR Number:",
                                    "value": "${prNumber}"
                                },
                                {
                                    "title": "Commit Author:",
                                    "value": "${prAuthor}"
                                }
                            ],
                            "spacing": "Medium",
                            "separator": true
                        },
                        {
                            "type": "TextBlock",
                            "text": "PR Title: ${prTitle}",
                            "wrap": true,
                            "spacing": "Medium",
                            "separator": true,
                            "maxLines": 2,
                            "size": "Small",
                            "fontType": "Monospace",
                            "weight": "Bolder",
                            "color": "Accent"
                        }
                    ],
                    "actions": [
                        {
                            "type": "Action.OpenUrl",
                            "title": "View ${appName} Build",
                            "url": "${env.BUILD_URL}",
                            "iconUrl": "https://i.ibb.co/Ks2JKfG/cloudbees-logo-icon-168396.png"
                        }
                    ]
                }
            }
        ]
    }
    """
    
    // Method to send a notification to MS Teams
    httpRequest(
        httpMode: 'POST',
        acceptType: 'APPLICATION_JSON',
        contentType: 'APPLICATION_JSON',
        url: workflowUrl,
        requestBody: payload
    )
}
