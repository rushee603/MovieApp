//package com.niit.usermovieservice.service;
//
//
//import com.niit.usermovieservice.model.Movie;
//import com.niit.usermovieservice.model.User;
//import com.niit.usermovieservice.proxy.UserAuthProxy;
//import com.niit.usermovieservice.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//
//    @Mock
//    private UserRepository userRepository ;
//    @Mock
//    private UserAuthProxy userAuthProxy;
//    @InjectMocks
//    private UserMovieServiceImp userMovieServiceImp ;
//
//     private User user;
//     private List<Movie> favList ;
//
//    private int genre_ids[]={28,18,36,10752} ;
//
//    @BeforeEach
//public void setUp(){
//
//       favList.add(new Movie(1,true,"sgfw",genre_ids,"en","heloo","hjervshja",89.76,"wjfkwjmf","10-02-2022","adf",true,900.65,657)) ;
//        user=new User("sathya@gmail.com","Sathya","Sathya@123",9740978523,favList) ;
//}
//
//    @Test
//    public void registerUserTest(){
//        when(userRepository.findById(user.getUserName())).thenReturn(Optional.ofNullable(null)) ;
//        when(userRepository.save(user)).thenReturn(user);
//    }
//
//
//
//}
