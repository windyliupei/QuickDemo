package com.hon.windy.quickapi.fek;

public class JsonRootBean {


        private String description;
        private String title;
        private Custom_content custom_content;
        public void setDescription(String description) {
            this.description = description;
        }
        public String getDescription() {
            return description;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setCustom_content(Custom_content custom_content) {
            this.custom_content = custom_content;
        }
        public Custom_content getCustom_content() {
            return custom_content;
        }



}
