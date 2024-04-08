package org.blogger.bloggerapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotesDto {
    private Long upVote;
    private Long downVote;
}
