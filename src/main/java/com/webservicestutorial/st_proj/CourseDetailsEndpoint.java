package com.webservicestutorial.st_proj;

import com.webservicestutorial.GetCourseDetailsRequest;
import com.webservicestutorial.GetCourseDetailsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import static com.webservicestutorial.GetCourseDetailsResponse.*;

/**
 * Created by Admin on 03.02.2018.
 */
@Endpoint
public class CourseDetailsEndpoint {

    private static final String NAMESPACE_URI = "http://someBookDetails.com/courses";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        GetCourseDetailsResponse response =  new GetCourseDetailsResponse();
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(request.getId());
        courseDetails.setName("Java EE 8 course");
        courseDetails.setDescription("some info");

        response.setCourseDetails(courseDetails);
        return response;
    }

}
