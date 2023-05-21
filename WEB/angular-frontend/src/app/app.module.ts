import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
//import { HTTP_INTERCEPTORS } from '@angular/common/http';
//import { TokenInterceptor } from './interceptor/TokenInterceptor';
//import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HomeComponentComponent} from "./HomeComponent/home-component/home-component.component";
import {LoginComponent} from "./login.component/login.component.component";
import {CommonModule} from "@angular/common";
import {RouterModule, Routes} from "@angular/router";
import { RegisterComponentComponent } from './register.component/register.component.component';

import {AuthServiceService} from "./Services/auth.service.service";

import {ConfigServiceService} from "./Services/config-service.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {TokenInterceptor} from "./interceptor/TokenInterceptor";
import {UserServiceService} from "./user-service.service";
import { HomePageComponent } from './home-page/home-page.component';
import {JWT_OPTIONS, JwtHelperService} from "@auth0/angular-jwt";
import { PasswordChangeComponent } from './password-change/password-change.component';
import { GroupCreateComponent } from './group-create/group-create.component';
import { PostsComponent } from './posts/posts.component';
import {PostService} from "./Services/post.service";
import {GroupService} from "./Services/group.service";
import { OnePostComponent } from './one-post/one-post.component';
import { GroupsComponent } from './groups/groups.component';
import { OneGroupComponent } from './one-group/one-group.component';
const routes: Routes = [
  { path: '', component: HomeComponentComponent },
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponentComponent },
  { path: 'HomePage', component: HomePageComponent },
  { path: 'passchange', component: PasswordChangeComponent},
  { path: 'GroupCreate', component: GroupCreateComponent},
  { path: 'post/:id', component: OnePostComponent},
  { path: 'groups', component: GroupsComponent},
  { path: 'group/:id', component: OneGroupComponent},

];
@NgModule({
  declarations: [
    OneGroupComponent,
    GroupsComponent,
    OnePostComponent,
    GroupCreateComponent,
    AppComponent,
    PasswordChangeComponent,
    HomeComponentComponent,
    LoginComponent,
    RegisterComponentComponent,
    HomePageComponent,
    PasswordChangeComponent,
    GroupCreateComponent,
    PostsComponent,
    OnePostComponent,
    GroupsComponent,
    OneGroupComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    RouterModule.forRoot(routes),
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    NoopAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [RouterModule],

  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  },AuthServiceService, { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService,GroupService,PostService,ConfigServiceService,UserServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
