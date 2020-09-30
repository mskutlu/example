import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ExampleSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [ExampleSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent],
})
export class ExampleHomeModule {}
