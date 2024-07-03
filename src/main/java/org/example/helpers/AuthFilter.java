package org.example.helpers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.example.dto.ErrorMessage;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import org.example.dao.DoctorDao;
import org.example.dao.PatientDao;

import java.io.IOException;
import java.util.*;


@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    DoctorDao doctorDao;
    @Inject
    PatientDao patientDao;

        @Override
        public void filter(ContainerRequestContext requestContext) throws IOException {
            if(!requestContext.getUriInfo().getPath().contains("secures")) return;
//نجيب من الهيدرز الاوثرايزيشن فقط
            List<String> authHeaders = requestContext.getHeaders().get("Authorization");//تاخذ الاوثرايزيشن ك ليست اوف سترينق
            //نتأكد من ان المعلومات موجوده لن ممكن مايكون دخل شي
            if (authHeaders != null && authHeaders.size() != 0) {
                String authHeader = authHeaders.get(0);//نجيب القيمة الأولى فيها
                //لأن القيمه بعد ماجبناها تحتوي على كلمة بيسك استدبلناها ب"" عشان نشيلها
                authHeader = authHeader.replace("Basic ", "");
                authHeader = new String(Base64.getDecoder().decode(authHeader));//ديكود ترجع بايت عسان كذا حطينا سترينق
                StringTokenizer tokenizer = new StringTokenizer(authHeader, ":");
                String user_email = tokenizer.nextToken();
                String user_password = tokenizer.nextToken();

                try {
                    if (doctorDao.loginDoctor(user_email,user_password) != null){
                        System.out.println("Doctor logged in");
                        return;
                    } else if (patientDao.loginPatien(user_email,user_password)!= null) {
                        System.out.println("Patient logged in");
                        return;
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            ErrorMessage err = new ErrorMessage();
            err.setErrorContent("Please login");
            err.setErrorCode(Response.Status.UNAUTHORIZED.getStatusCode());
            err.setDocumentationUrl("https://google.com");

            Response res = Response.status(Response.Status.UNAUTHORIZED)
                    .entity(err)
                    .build();

            requestContext.abortWith(res);
        }
    }


