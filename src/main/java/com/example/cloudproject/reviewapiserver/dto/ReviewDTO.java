package com.example.cloudproject.reviewapiserver.dto;

import com.example.cloudproject.reviewapiserver.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

public class ReviewDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateRequest {
        private Long storeId;
        private Long userId;
        private String menu;
        private Byte grade;
        private String comment;
        private List<Short> hashtags;
        private Boolean doUploadImage;
        private Boolean isHidden;
        private Boolean isAnonymous;

        public Review toEntity() {
            return Review.builder()
                    .storeId(storeId)
                    .userId(userId)
                    .menu(menu)
                    .grade(grade)
                    .comment(comment)
                    .hashtag1Id(hashtags.get(0))
                    .hashtag2Id(hashtags.get(1))
                    .hashtag3Id(hashtags.get(2))
                    .imageUuid(doUploadImage ? UUID.randomUUID() : null)
                    .isHidden(isHidden)
                    .isAnonymous(isAnonymous)
                    .build();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateResponse {
        private Boolean doUploadImage;
        private UUID imageUuid;

        public static CreateResponse from(Review review) {
            return CreateResponse.builder()
                    .doUploadImage(review.getImageUuid() != null)
                    .imageUuid(review.getImageUuid())
                    .build();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateHashtagAndGradeRequest {

        private Long storeId;
        private HashtagAndGradeInfo info;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class HashtagAndGradeInfo {

        private List<Integer> hashtags;
        private Byte grade;
    }
}
