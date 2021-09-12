import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AuthComponent} from "./auth/auth.component";

const routes: Routes = [{path:'', loadChildren:() => import("./home/home.module").then(m => m.HomeModule)}, {path:'login', component:AuthComponent},{path:'register', component:AuthComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
