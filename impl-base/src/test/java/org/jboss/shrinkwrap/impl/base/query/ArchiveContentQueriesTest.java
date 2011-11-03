/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.shrinkwrap.impl.base.query;

import java.util.List;

import junit.framework.Assert;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.query.ArchiveContentQueries;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;

/**
 * @author Davide D'Alto
 */
public class ArchiveContentQueriesTest
{
   @Test
   public void testQueryCreation() throws Exception
   {
      ArchiveContentQuery query = ArchiveContentQueries.createQuery(null);

      Assert.assertNotNull(query);
   }

   @Test
   public void testname() throws Exception
   {
      JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addAsResource(new StringAsset("Java archive"), "readme.txt");
      WebArchive war = ShrinkWrap.create(WebArchive.class).addAsResource(new StringAsset("Web archive"), "readme.txt");

      EnterpriseArchive ear = ShrinkWrap
            .create(EnterpriseArchive.class)
            .addAsModule(jar)
            .addAsModule(war);

      ArchiveContentQuery query = ArchiveContentQueries.createQuery(EnterpriseArchive.class).addCriteria(
            new TypedAssetCriteria(WebArchive.class));
      List<WebArchive> wars = query.forArchive(ear).getResultList();

      Assert.assertNotNull(wars);
      Assert.assertFalse(wars.isEmpty());
      Assert.assertEquals(1, wars.size());
      Assert.assertEquals(war, wars.get(0));
   }
}
