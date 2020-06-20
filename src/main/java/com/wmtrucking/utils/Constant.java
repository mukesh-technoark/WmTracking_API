package com.wmtrucking.utils;

public enum Constant {
    MODE {
        @Override
        public String toString() {
            return "Live";
//            return "Local";
        }
    },
    WEBURL {
        @Override
        public String toString() {
            return MODE.toString().equals("Local") ? "http://192.168.1.46:8080/wmtrucking/" : "http://62.252.239.190/wmt";

// return "http://54.171.107.227:8081/";
//            return "http://192.168.1.46:8080/wmtrucking/";
        }
    }, INVOICEURL {
        @Override
        public String toString() {
            return WEBURL + "pdfAPI/";
        }
    },
    PUSH_CERTIFICATE {
        @Override
        public String toString() {
            return "push_certi_dev.p12";
        }
    },
    JWT_KEY {
        @Override
        public String toString() {
            return "X3qGRkZY-BB786BB-XYZ6653-HMAC256";
        }
    },
    FILEUPLOAD_PATH {
        @Override
        public String toString() {
            return "/upload/wmt/";

//            return MODE.toString().equals("local") ? "/Users/admin/Downloads/Uploads/" : "/upload/equi360v3/";
        }
    },
    APPTOKEN {
        @Override
        public String toString() {
            return "a7voxyzjGBzm-Trucking-2019-UoWZAkBB1sZ";
        }
    },
    TOKEN {
        @Override
        public String toString() {
            return "apptoken"; //To change body of generated methods, choose Tools | Templates.
        }
    },
    DEVICETOKEN {
        @Override
        public String toString() {
            return "devicetoken"; //To change body of generated methods, choose Tools | Templates.
        }
    },
    AUTHORIZATION {
        @Override
        public String toString() {
            return "Authorization"; //To change body of generated methods, choose Tools | Templates.
        }
    },
    PUBLIC_FOLDER {
        @Override
        public String toString() {
            return "/Public";
        }
    },
    INVALIDAPPTOKEN {
        @Override
        public String toString() {
            return "Invalid token";
        }

    },
    EVERYONE {
        @Override
        public String toString() {
            return "Everyone";
        }

    },
    SYNDICATE {
        @Override
        public String toString() {
            return "Syndicate";
        }

    },
    AESENCRYPTKEY {
        @Override
        public String toString() {
            return "78666"; //To change body of generated methods, choose Tools | Templates.
        }
    },
    ALLFIELDSREQ {
        @Override
        public String toString() {
            return "All the fields are mandatory."; //To change body of generated methods, choose Tools | Templates.
        }
    },
    LOGINERROR {
        @Override
        public String toString() {
            return "Please Login to continue"; //To change body of generated methods, choose Tools | Templates.
        }
    },
    ACTIVE {
        @Override
        public String toString() {
            return "Active"; //To change body of generated methods, choose Tools | Templates.
        }

    },
    STARTED {
        @Override
        public String toString() {
            return "Started";
        }

    },
    ENDED {
        @Override
        public String toString() {
            return "Ended";
        }

    },
    COMPLETED {
        @Override
        public String toString() {
            return "Completed";
        }

    },
    ACCEPTED {
        @Override
        public String toString() {
            return "Accepted"; //To change body of generated methods, choose Tools | Templates.
        }

    }, CLOSE {
        @Override
        public String toString() {
            return "Close"; //To change body of generated methods, choose Tools | Templates.
        }

    },
    PENDING {
        @Override
        public String toString() {
            return "Pending";
        }

    }, INACTIVE {
        @Override
        public String toString() {
            return "Inactive"; //To change body of generated methods, choose Tools | Templates.
        }
    },
    SUCCESSFUL {
        @Override
        public String toString() {
            return "Successful"; //To change body of generated methods, choose Tools | Templates.
        }
    },
    DEACTIVE {
        @Override
        public String toString() {
            return "Deactive"; //To change body of generated methods, choose Tools | Templates.
        }
    },
    DELETED {
        @Override
        public String toString() {
            return "Deleted"; //To change body of generated methods, choose Tools | Templates.
        }
    },
    RECORD_SAVED {
        @Override
        public String toString() {
            return "Your record has been saved successfully.";
        }

    },
    RECORD_UPDATE {
        @Override
        public String toString() {
            return "Your record has been updated successfully.";
        }

    },
    TRAINER {
        @Override
        public String toString() {
            return "Trainer";
        }

    },
    RECORD_DELETE {
        @Override
        public String toString() {
            return "Your record has been deleted successfully.";
        }
    },
    NO_RECORD {
        @Override
        public String toString() {
            return "No records found.";
        }
    },
    IMAGE {
        @Override
        public String toString() {
            return "Image";
        }

    },
    VIDEO {
        @Override
        public String toString() {
            return "Video";
        }

    },
    AUDIO {
        @Override
        public String toString() {
            return "Audio";
        }

    }// for web api
    , WEB_AUTH_TOKEN {
        @Override
        public String toString() {
            return "AuthToken"; //To change body of generated methods, choose Tools | Templates.
        }
    }, WEB_TOKEN {
        @Override
        public String toString() {
            return "WebToken"; //To change body of generated methods, choose Tools | Templates.
        }
    },
    ISPUBLIC {
        @Override
        public String toString() {
            return "IsPublic";
        }
    },
    LIVE {
        @Override
        public String toString() {
            return "Live";
        }
    },
    UNDER_MAINTENANCE {
        @Override
        public String toString() {
            return "Under Maintenance";
        }
    }, SENT {
        @Override
        public String toString() {
            return "Send"; //To change body of generated methods, choose Tools | Templates.
        }
    },
    CANCELLED {
        @Override
        public String toString() {
            return "Cancelled"; //To change body of generated methods, choose Tools | Templates.
        }
    }, CREATE_NEW_INVOICE {
        @Override
        public String toString() {
            return "Create new Invoice";
        }

    }, ADD_TO_NEXT_INVOICE {
        @Override
        public String toString() {
            return "Add to next invoice";
        }

    }, DO_NOT_CREATE_INVOICE {
        @Override
        public String toString() {
            return "Do not create Invoice";
        }
    }, SINGLEOWNER {
        @Override
        public String toString() {
            return "Single Owner";
        }

    },
    PARTIALLY {
        @Override
        public String toString() {
            return "Partially"; //To change body of generated methods, choose Tools | Templates.
        }
    },
    PARTNERSHIP {
        @Override
        public String toString() {
            return "Partnership";
        }

    }
}
