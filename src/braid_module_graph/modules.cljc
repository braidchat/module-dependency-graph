(ns braid-module-graph.modules)

; new? - does this functionality already exist in Braid?
; provides? - does this module provide functions for other modules to call?

(def modules
  [{:id "legend"
    :modules [{:id "new"
               :new? true}
              {:id "in-core"
               :new? false}
              {:id "extracted"
               :extracted? true}]}
   
   {:id "braid-server"
    :modules [{:id "system-settings"
               :new? false
               :provides? true
               :description "Allows modules to require configuration that needs to be set by the system administrator (such as api-keys)"}
              {:id "jobs"
               :new? false
               :provides? true
               :description "Allows modules to register themselves for time-based triggers."}
              {:id "email"
               :new? false
               :provides? true
               :description "Allows modules to send emails to a user."}
              {:id "state"
               :new? false
               :provides? true
               :description "Allows a module to define its own state and make changes."}]}

   {:id "braid-web-client"
    :description "Implements the Braid web client.
                 Allows modules to..."

    :modules [{:id "error"
               :new? false
               :provides? true
               :description "Allows modules to show an error to the user."}
              {:id "page"
               :new? false
               :provides? true
               :description "Allows modules to define top-level pages with their own URL and content."}
              {:id "user-menu"
               :new? false
               :provides? true
               :description "Allows modules to add links to the user menu."}
              {:id "group-header"
               :new? false
               :provides? true
               :description "Implements the group header UI.
                            Allows modules to add buttons to the header."}]} 

  {:id "inbox"
   :descriptions "UI for inbox"
   :new? false
   :provides? false
   :deps #{"group-page"
           "group-header"}}

   {:id "conversation-ui"
    :modules [{:id "autocomplete"
               :new? false
               :provides? true
               :description "Allows modules to include results in the autocomplete that shows when composing a message."}
              {:id "message-render"
               :new? false
               :provides? true}
              {:id "message-footer-ui"
               :new? true
               :provides? true
               :description "Allows modules to display UI after a message."}
              {:id "compose-actions"
               :new? true
               :provides? true}
              {:id "conversation-header"
               :new? false
               :provides? true
               :description "Provides modules ways to add UI to the conversation header."}
              {:id "message-preprocess"
               :new? true
               :provides? true
               :description "Allows modules to modify messages before they are actually sent,
                            including the potential to prevent messages from sending."}]}

   {:id "group-page"
    :new? false
    :provides? true
    :description "Allows modules to define group pages with their own URL and content."
    :deps #{"page"}}

   {:id "conversations"
    :new? false
    :provides? true
    :description "Allows modules to create 'conversations', add messages to conversations, assign tags and users.
                  Allows users to close and mute conversations."
    :deps #{"notifications"
            "state"
            "error"
            "users"}}

   {:id "groups"
    :description "Allows for the creation of groups"
    :deps #{"state"}}

   {:id "users"
    :new? false
    :provides? true
    :descriptions "Allows for creation of users."
    :deps #{"state"}}

   {:id "new-group-creation-flow"
    :descriptions "Implements flow for creating new groups."
    :new? false
    :provides? false
    :deps #{"page"
            "users"
            "groups"}}

   {:id "notifications"
    :new? false
    :provides? true
    :description "Allows modules to send notifications."
    :deps #{"user-settings"}}

   {:id "user-settings"
    :new? false
    :provides? true
    :description "Implements the user settings page.
                  Allows modules to require configuration that will show up on that page."
    :deps #{"page"
            "user-menu"}}

   {:id "group-settings"
    :new? false
    :provides? true
    :description "Implements the group settings page.
                  Allows modules to require configuration that will show up on that page."
    :deps #{"group-page"
            "group-header"}}

   {:id "emoji"
    :new? false
    :provides? true
    :extracted? true
    :description "Allows modules to define emoji, that will show in autocomplete and messages."
    :deps #{"autocomplete"
            "message-render"
            "group-settings"}}

   {:id "user-mentions"
    :new? false
    :provides? false
    :description "Allows mentioning of users in messages.
                  Displays users in autocomplete.
                  Highlights users in messages."
    ; TODO also marks conversation to belong to the user 
    :deps #{"autocomplete"
            "message-render"}}

   {:id "tags"
    :new? false
    :provides? true
    :description "Allows for the creation of tags, associating conversations with tags,
                  associating users with tags."
    :deps #{"state"
            "conversations"
            "users"}}

   {:id "tag-mentions"
    :new? false
    :provides? false
    :description "Allows mentioning of tags in messages.
                 Displays tags in autocomplete.
                 Highlights tags in messages."
    ; TODO also marks conversation to belong to the tag 
    :deps #{"autocomplete"
            "message-render"
            "tags"}}

   {:id "emojione-emoji"
    :new? false
    :provides? false
    :extracted? true
    :description "Provides emoji-one style emojis"
    :deps #{"emoji"}}

   {:id "twemoji"
    :new? true
    :provides? false
    :description "Provides twitter style emojis"
    :deps #{"emoji"}}

   {:id "custom-emoji"
    :new? false
    :provides? false
    :extracted? true
    :description "Allows group admins to upload and set custom group emoji."
    :deps #{"emoji"
            "group-settings"}}

   {:id "big-emoji"
    :new? false
    :provides? false
    :extracted? true
    :description "Embiggens emoji when it is the sole content of a message"
    :deps #{"emoji"}}

   {:id "auth"
    :new? false
    :description "Implements user sessions, with the implementation of specific method(s) of authentication left to other modules."
    :provides? true}

   {:id "oauth"
    :new? false
    :provides? true
    :description "Provides a way for modules to register various oAuth services"
    :deps #{"auth"}}

   {:id "google-oauth"
    :new? true
    :provides? false
    :description "Allows users to login with their Google account."
    :deps #{"oauth"
            "system-settings"}}

   {:id "github-oauth"
    :new? false
    :provides? false
    :description "Allows users to login with their Github account."
    :deps #{"oauth"
            "system-settings"}}

   {:id "facebook-oauth"
    :new? true
    :provides? false
    :description "Allows users to login with their Facebook account."
    :deps #{"oauth"
            "system-settings"}}

   {:id "pass-auth"
    :new? false
    :provides? false
    :description "Allows users to login with a login/password account."
    :deps #{"auth"
            "state"
            "user-settings"}}

   {:id "email-digest"
    :new? false
    :provides? false
    :description "Sends email digests to user."
    :deps #{"user-settings"
            "email"
            "jobs"
            "conversations"}}

   {:id "files"
    :new? false
    :provides? true
    :description "Allows for the sharing of files (upload, mention, search in autocomplete, display), with the specific implementation of storage left to other modules."
    :deps #{"compose-actions"
            "autocomplete"}}

   {:id "dropbox"
    :new? true
    :provides? false
    :description "Uploads files directly to Dropbox.
                 Maintains references to files in Braid if they are moved in Dropbox.
                 Add Dropbox file results to autocomplete so files can be mentioned in messages."
    :deps #{"files"}}

   {:id "s3"
    :new? false
    :provides? false
    :deps #{"files"}}

   {:id "search"
    :descriptions "UI for searching / navigating past conversations."
    :new? false
    :provides? true
    :deps #{"group-page"
            "conversations"
            "group-header"}}

   {:id "user-avatar"
    :description "Allows users to have an avatar"
    :new? false
    :provides? false
    :deps #{"user-settings"
            "users"
            "state"}}

   {:id "embeds"
    :new? false
    :provides? true
    :extracted? true
    :deps #{"message-footer-ui"}}

   {:id "google-maps-embeds"
    :new? true
    :provides? false
    :description "Displays embedded Google Maps when a maps URL is in a message."
    :deps #{"embeds"}}

   {:id "video-embeds"
    :new? false
    :provides? false
    :extracted? true
    :description "Displays embedded video player when a video URL is in a message."
    :deps #{"embeds"}}

   {:id "youtube-video-embeds"
    :new? true
    :provides? false
    :description "Displays embedded video player when a youtube URL is in a message."
    :deps #{"embeds"}}

   {:id "soundcloud-embeds"
    :new? true
    :provides? false
    :description "Displays embedded soundcloud player when a soundcloud URL is in a message."
    :deps #{"embeds"}}

   {:id "website-embeds"
    :new? false
    :provides? false
    :extracted? true
    :description "Displays embedded website-info for URLs."
    :deps #{"embeds"}}

   {:id "image-embeds"
    :new? false
    :provides? false
    :extracted? true
    :description "Displays embedded image when an image URL is in a message."
    :deps #{"embeds"}}

   {:id "code-display"
    :new? false
    :provides? true
    :deps #{"message-render"}}

   {:id "clojure-code-display"
    :new? false
    :provides? false
    :deps #{"code-display"}}

   {:id "long-reply"
    :new? true
    :provides? false
    :description "Provides UI for writing a long message."
    :deps #{"compose-actions"}}

   {:id "email-bridge"
    :new? true
    :provides? false
    :description "Deep integration between an email account and Braid.
                  Incoming emails display as conversations.
                  Can reply from Braid."
    :deps #{"conversations"
            "long-reply"
            "group-settings"}}

   {:id "stars"
    :new? true
    :provides? false
    :deps #{"state"
            "conversation-header"
            "group-header"
            "group-page"}}

   {:id "invites"
    :new? false
    :provides? false
    :deps #{"group-page"
            "email"
            "user-menu"}}

   {:id "reactions"
    :new? true
    :provides? false
    :deps #{"message-footer-ui"
            "state"}}

   {:id "auto-translate"
    :new? true
    :provides? false 
    :deps #{"message-render"
            "message-footer-ui"
            "user-settings"}}

   {:id "links-in-message"
    :new? false
    :provides? false
    :description "Detects URLs in messages and display as clickable links."
    :deps #{"message-render"}}

   {:id "keyboard-shortcuts"
    :new? true
    :provides? true
    :description "Provides other modules a way to declare and listen to keyboard shortcuts.
                 Displays shorcuts in UI when pressing TAB"}

   {:id "slack-bridge"
    :new? true
    :provides? false
    :deps #{"conversations"}}

   {:id "asana-bridge"
    :new? true
    :provides? false
    :description "Deep integration with Asana.
                 Tasks and projects show up in autocomplete."
    :deps #{"autocomplete"
            "conversations"}}

   {:id "github-bridge"
    :new? true
    :provides? false
    :description "Syncs Braid conversations with Github Issues"
    :deps #{"conversations"
            "group-settings"}}

   {:id "voice-and-video-chat"
    :new? true
    :provides? false}

   {:id "gmail-bridge"
    :new? true
    :provides? false
    :deps #{"email-bridge"}}

   {:id "google-groups-bridge"
    :new? true
    :provides? false
    :description "Deep integration with Google Groups."
    :deps #{"conversations"} ; TODO
    }

   {:id "discourse-bridge"
    :new? true
    :provides? false
    :description "Deep integration with Discourse."
    :deps #{"conversations"} ; TODO
    }

   {:id "password-obfuscator"
    :new? true
    :provides? false
    :description "Detects potential passwords in a message and warns user before allowing them to post."
    :deps #{"message-preprocess"}}

   {:id "quests"
    :new? false
    :extracted? true
    :provides? true
    :description "Provides modules a way to define 'quests' - tasks for the user to perform to learn about how to use Braid functionality."
    :deps #{"state"
            "group-header"}}

   {:id "markdown"
    :new? true
    :provides? false
    :description "Support markdown when composing messages."
    :deps #{"message-render"}}

   {:id "braid-messaging"
    :new? false
    :provides? false
    :description "Provides the core braid messaging experience."
    :deps #{"conversations"
            "quests"}}

   {:id "payments"
    :new? false
    :provides? false
    :extracted? true
    :description "Implments way for group owner to pay a subscription to administrator"
    :deps #{"group-settings"
            "group-header"
            "users"}}

   {:id "rest-api"
    :new? true
    :provides? true
    :description "Exposes a REST API to send messages."}

   ])
