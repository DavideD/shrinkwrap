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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.Node;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * @author Davide D'Alto
 */
public class ArchiveContentQuery
{

   private TypedAssetCriteria criteria;
   private Archive<?> archive;

   public ArchiveContentQuery addCriteria(TypedAssetCriteria typedAssetCriteria)
   {
      this.criteria = typedAssetCriteria;
      return this;
   }

   public List<WebArchive> getResultList()
   {
      Map<ArchivePath, Node> content = archive.getContent();
      List<WebArchive> results = new ArrayList<WebArchive>();
      for (Entry<ArchivePath, Node> entry : content.entrySet())
      {
         ArchivePath key = entry.getKey();
         Node value = entry.getValue();
         if (criteria.matches(value))
            results.add(null);
         
      }
      return null;
   }

   public ArchiveContentQuery forArchive(Archive ear)
   {
      this.archive = ear;
      return this;
   }

}
